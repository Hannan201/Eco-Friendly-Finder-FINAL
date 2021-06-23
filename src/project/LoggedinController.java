package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoggedinController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private Stage stage;
    private String currentUsername;
    private SettingsController settingsController;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentUsername(String newUsername) {
        this.currentUsername = newUsername;
    }

    public void searchScene() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("searchScene.fxml"));
        changeScene("settingsScene.fxml", loader);
        if (!Objects.isNull(settingsController.getCurrentUsername())) {
            currentUsername = settingsController.getCurrentUsername();
            loader.setController(settingsController);
        }
    }

    public void viewScene() {

    }

    public void settingsScene() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("settingsScene.fxml"));
        settingsController.setCurrentUsername(currentUsername);
        loader.setController(settingsController);
        changeScene("settingsScene.fxml", loader);
    }

    public void changeScene(String name, FXMLLoader loader) {
        try {
            BorderPane change = loader.load();
            borderPane.setCenter(change);
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingsController = new SettingsController();
    }
}
