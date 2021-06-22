package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene homeScene;

    public void setHomeScene(Scene scene) {
        this.homeScene = scene;
    }

    public void goBack(ActionEvent actionEvent) {
        stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }

    public void handleSignupButton(ActionEvent actionEvent) {
        AccountData accountData = new AccountData();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ((username == null) || (password == null)
                || username.isBlank() || password.isBlank()) {
            popUpWindow("Username or password cannot be blank!");
        } else if (!accountData.createAccount(username + ".Cho", password)) {
            popUpWindow("Account already exists!");
            usernameField.clear();
            passwordField.clear();
        } else {
            popUpWindow("Account successfully created!");
            goBack(actionEvent);
        }
    }

    private void popUpWindow(String message) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("okayBox.fxml"));
        PopupController popupController;
        Scene scene;
        Stage stage;
        Parent root;
        try {
            root = loader.load();
            popupController = loader.getController();
            popupController.getErrorLabel().setText(message);
            scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            popupController.setStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error");
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
