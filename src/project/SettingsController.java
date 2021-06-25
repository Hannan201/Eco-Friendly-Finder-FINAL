package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsController {

    private Stage stage;

    @FXML
    private PasswordField oldPassword, newPassword;

    @FXML
    private TextField newUsername;

    private String currentUsername;
    private Account account;
    private AccountData accountData;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentUsername(String newUsername) {
        this.currentUsername = newUsername;
        accountData = new AccountData();
        account = new Account(this.currentUsername, accountData.getPassword(this.currentUsername));
    }

    public String getCurrentUsername() {
        return this.currentUsername;
    }

    public void handleUsername() {
        openWindow("changeName.fxml");
    }

    public void handlePassword() {
        openWindow("changePass.fxml");
    }

    public void handleLogout() {
        openWindow("signOut.fxml");
    }

    public void closeWindow() {
        stage.close();
    }

    public void changePass() {
        String oldPass = oldPassword.getText();
        String newPass = newPassword.getText();

        if ((oldPass == null) || (newPass == null) ||
            oldPass.isBlank() || newPass.isBlank()) {
            popUpWindow("New and old password cannot be blank!");
            oldPassword.clear();
            newPassword.clear();
        } else if (!oldPass.equals(accountData.getPassword(currentUsername))) {
            popUpWindow("Incorrect password for your current account");
            oldPassword.clear();
            newPassword.clear();
        } else {
            account.changePassword(newPass);
            popUpWindow("Password successfully changed");
            closeWindow();
        }
    }

    public void changeName() {
        String newName = newUsername.getText();

        if ((newName == null) || (newName.isEmpty())) {
            popUpWindow("New username cannot be blank!");
            newUsername.clear();
        } else {
            account.changeUsername(newName);
            setCurrentUsername(newName);
            popUpWindow("Username changed!");
            closeWindow();
        }
    }

    public void popUpWindow(String message) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("okayBox.fxml"));
        Scene scene;
        Stage stage;
        Parent root;
        try {
            root = loader.load();
            PopupController popupController = loader.getController();
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

    public void openWindow(String name) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource(name)));
        loader.setController(this);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
