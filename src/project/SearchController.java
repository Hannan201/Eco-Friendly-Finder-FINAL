package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {

    private String query;
    private int numOfLinks;

    @FXML
    private VBox holder;

    private boolean again = true;

    @FXML
    private ScrollPane mainScroller;

    @FXML
    private Button leaveButton;

    private ArrayList<Product> products;
    private String username;
    private Map<Button, String> id;
    private Stage stage;
    private Stage stageTwo;
    private Scene previous;

    public void setPrevious(Scene scene) {
        previous = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setQuery(String string) {
        this.query = string;
    }

    public void setUsername(String string) {
        this.username = string;
    }

    public void setNumOfLinks(int integer) {
        this.numOfLinks = integer;
    }

    public void leaveProgram() {
        openWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (again) {
            query = "Eco " + query;
            GettingLinks gettingLinks = new GettingLinks();
            products = gettingLinks.searchAmazon(numOfLinks, query);
            mainScroller.setPannable(false);
            mainScroller.getStylesheets().add(this.getClass().getResource("sideBar.css").toExternalForm());
            mainScroller.setFitToWidth(true);
            mainScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            mainScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            Map<Button, HBox> container = new HashMap<>();
            id = new HashMap<>();

            for (int i = 0; i < products.size(); i++) {
                String toAdd = "";
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

                String details = "Name: " + products.get(i).getName() + "\n" +
                        "Price: " + products.get(i).getPrice() +
                        ", Rating: " + products.get(i).getRating() + "\n";

                toAdd += details + products.get(i).getLink();

                Label information = new Label(details);
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

                Hyperlink hyperlink = new Hyperlink(products.get(i).getLink());
                hyperlink.setFont(Font.font(25));
                hyperlink.setTextFill(Color.WHITE);
                hyperlink.setWrapText(true);
                hyperlink.setAlignment(Pos.CENTER_LEFT);
                hyperlink.setPrefSize(874, 123);

                lastLine.getChildren().addAll(linkStart, hyperlink);

                Button button = new Button("Save");
                button.setPrefSize(149, 34);
                button.setTextFill(Color.BLACK);
                button.setFont(Font.font(25));
                button.styleProperty().set("-fx-background-radius: 0; -fx-background-color: #2ade1d");

                id.put(button, toAdd);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Button button = (Button) actionEvent.getSource();
                        storeData(username, button);
                        holder.getChildren().remove(container.get(button));
                    }
                });

                vBox.getChildren().addAll(information, lastLine);

                hBox.getChildren().addAll(vBox, button);

                container.put(button, hBox);
            }

            for (Button save : container.keySet()) {
                holder.getChildren().add(container.get(save));
            }
        }
        again = false;
    }

    public void storeData(String username, Button button) {
        MainFile mainFile = new MainFile(username + ".Cho");
        mainFile.openForWriting(true);
        mainFile.writeToFile(id.get(button) + "\n");
        mainFile.closeForWriting();
    }

    public void openWindow() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("leave.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stageTwo = new Stage();
            stageTwo.setScene(scene);
            stageTwo.initModality(Modality.APPLICATION_MODAL);
            stageTwo.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelled() {
        stageTwo.close();
    }

    public void complete() {
        stageTwo.close();
        stage.setScene(previous);
    }
}
