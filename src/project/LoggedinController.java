package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class LoggedinController {

    @FXML
    private BorderPane borderPane;

    private Stage stage;
    private String user;

    public void setUser(String string) {
        this.user = string;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void searchScene(ActionEvent actionEvent) {
        changeScene(actionEvent, "searchScene.fxml");
    }

    public void viewScene() {

    }

    public void settingsScene(ActionEvent actionEvent) {
        changeScene(actionEvent, "settingsScene.fxml");
    }

    public void changeScene(ActionEvent actionEvent, String name) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(name));
        Scene scene = ((Node)(actionEvent.getSource())).getScene();

        try {
            BorderPane change = loader.load();
            SettingsController settingsController =
                    loader.getController();
            settingsController.setCurrentUser(user);
            borderPane.setCenter(change);
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
        }
    }
}
