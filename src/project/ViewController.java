package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class ViewController implements Initializable {

    @FXML
    private ScrollPane mainScroller;

    @FXML
    private VBox holder;

    private String username;
    private Map<Button, String> id;

    public void setUsername(String name) {
        this.username = name;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountData accountData = new AccountData();

        mainScroller.setPannable(false);
        mainScroller.getStylesheets().add(this.getClass().getResource("sideBar.css").toExternalForm());
        mainScroller.setFitToWidth(true);
        mainScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Map<Button, HBox> container = new HashMap<>();
        id = new HashMap<>();
        MainFile mainFile = new MainFile(username + ".Cho");
        mainFile.openForReading();
        mainFile.getLine();
        String line;

        while ((line = mainFile.getLine()) != null) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(30);
            hBox.setMaxSize(1165, 244);
            hBox.setMinSize(1165, 244);
            hBox.setPrefSize(1165, 244);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setPadding(new Insets(0, 0, 0, 10));
            vBox.setPrefSize(938, 471);

            String name = line;
            String priceAndRating = mainFile.getLine();

            Label information = new Label(name + "\n" + priceAndRating);
            information.setFont(Font.font(25));
            information.setTextFill(Color.WHITE);
            information.setWrapText(true);
            information.setAlignment(Pos.TOP_LEFT);
            information.setPrefSize(912, 111);

            HBox lastLine = new HBox();
            lastLine.setAlignment(Pos.TOP_LEFT);
            lastLine.setPrefSize(928, 129);

            Label linkStart = new Label("Link:");
            linkStart.setFont(Font.font(25));
            linkStart.setTextFill(Color.WHITE);
            linkStart.setAlignment(Pos.CENTER_LEFT);
            linkStart.setPrefSize(62, 52);

            String link = mainFile.getLine();

            Hyperlink hyperlink = new Hyperlink(link);
            hyperlink.setFont(Font.font(25));
            hyperlink.setTextFill(Color.WHITE);
            hyperlink.setWrapText(true);
            hyperlink.setAlignment(Pos.CENTER_LEFT);
            hyperlink.setPrefSize(874, 123);

            lastLine.getChildren().addAll(linkStart, hyperlink);

            Button button = new Button("Remove");
            button.setPrefSize(149, 34);
            button.setTextFill(Color.WHITE);
            button.setFont(Font.font(25));
            button.styleProperty().set("-fx-background-radius: 0; -fx-background-color: red");

            String toAdd = name + "\n" + priceAndRating
                    + "\n" + link;

            id.put(button, toAdd);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String password = accountData.getPassword(username);
                    Button button = (Button) actionEvent.getSource();
                    holder.getChildren().remove(container.get(button));
                    id.remove(button);
                    MainFile file = new MainFile(username + ".Cho");
                    file.openForWriting(false);
                    file.writeToFile(username + "," + password + "\n");
                    for (Button b : id.keySet()) {
                        file.writeToFile(id.get(b) + "\n");
                    }
                    file.closeForWriting();
                }
            });

            vBox.getChildren().addAll(information, lastLine);

            hBox.getChildren().addAll(vBox, button);

            container.put(button, hBox);
        }

        mainFile.closeForReading();

        for (Button save : container.keySet()) {
            holder.getChildren().add(container.get(save));
        }
    }
}
