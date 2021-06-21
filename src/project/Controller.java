package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label label;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button ExitButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String s;
    private FXMLLoader loader;
    private boolean close;

    public void cancelPrompt() {
        close = false;
        stage.close();
    }

    public void exitProgram() {
        close = true;
        stage.close();
    }

    public boolean getStatus() {
        return close;
    }

    public void init(FXMLLoader loader, Parent root) {
        this.loader = loader;
        this.root = root;
        scene = new Scene(root);
    }

    public void exitPrompt() {
        stage = new Stage();
        stage.setTitle("Are you sure?");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
