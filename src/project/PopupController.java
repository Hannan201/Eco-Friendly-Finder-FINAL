package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class PopupController {

    @FXML
    private Label errorLabel;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleOkayButton() {
        closeScene();
    }

    public void closeScene() {
        if (!Objects.isNull(stage))
            stage.close();
    }

    public Label getErrorLabel() {
        return this.errorLabel;
    }

}
