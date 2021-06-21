package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private Scene homeScene;
    private Stage stage;
    private Scene scene;

    public void setHomeScene(Scene scene) {
        this.homeScene = scene;
    }

    public void goBack(ActionEvent actionEvent) {
        stage = (Stage)((Node)(actionEvent.getSource())).getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }

    public void handleLoginButton(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!Account.accountIsMade(username)) {

        } else {

        }
    }

}
