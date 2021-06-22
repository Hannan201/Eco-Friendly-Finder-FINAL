package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Console;
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

    public void handleSignupButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (Account.accountIsMade(username + ".Cho")) {
            AccountData accountData = new AccountData();
            accountData.createAccount(username, password);
        } else {

        }
    }

    private void popUpWindow() {

    }
}
