package project;

import javafx.application.Application;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultTest extends Application implements Initializable {

    @FXML
    private ScrollPane mainScroller;

    private ArrayList<Product> products;

    @FXML
    private VBox holder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("results.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GettingLinks gettingLinks = new GettingLinks();
        products = gettingLinks.searchAmazon(5, "Water Bottle");
        System.out.println(products.size());

        mainScroller.setPannable(false);
        mainScroller.getStylesheets().add(this.getClass().getResource("sideBar.css").toExternalForm());
        mainScroller.setFitToWidth(true);
        mainScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        ArrayList<HBox> container = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(30);
            hBox.setMaxSize(1165, 244);
            hBox.setMinSize(1165, 244);
            hBox.setPrefSize(1165, 244);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setPadding(new Insets(0,0,0,10));
            vBox.setPrefSize(938, 471);

            String details = "Name: " + products.get(i).getName() + "\n" +
                             "Price: " + products.get(i).getPrice() +
                             " Rating: " + products.get(i).getRating() + "\n";

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

            vBox.getChildren().addAll(information, lastLine);

            hBox.getChildren().addAll(vBox, button);

            container.add(hBox);
        }

        for (HBox box : container) {
            holder.getChildren().add(box);
        }
    }
}
