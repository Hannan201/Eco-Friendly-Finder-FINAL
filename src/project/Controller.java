package project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    private Stage stage;
    private Scene scene;
    private String s;
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
        scene = new Scene(root);
    }

    public void exitPrompt() {
        stage = new Stage();
        stage.setTitle("Are you sure?");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
