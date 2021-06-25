package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoggedinController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField searchField;

    @FXML
    private Button start;

    @FXML
    private TextField amount;

    private Stage stage;
    private Stage finalStage;
    private String currentUsername;
    private SettingsController settingsController;
    private SearchController searchController;
    private LoggedinController loggedinController;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentUsername(String newUsername) {
        this.currentUsername = newUsername;
    }

    public void setLoggedInController(LoggedinController loggedInController) {
        this.loggedinController = loggedInController;
    }

    public void setRoot(BorderPane parent) {
        this.borderPane = parent;
    }

    public void searchScene() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("searchScene.fxml"));
        loader.setController(loggedinController);
        if (!Objects.isNull(settingsController.getCurrentUsername())) {
            currentUsername = settingsController.getCurrentUsername();
        }
        try {
            Parent root = loader.load();
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSearch() {
        String search = searchField.getText();

        if (search == null || search.isEmpty()) {
            popUpWindow("Product field cannot be blank");
        } else {
            int numOfProducts = numberOfProducts();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("results.fxml"));
            loader.setController(searchController);
            try {
                searchController.setQuery(search);
                searchController.setNumOfLinks(numOfProducts);
                searchController.setUsername(currentUsername);
                searchController.setStage(stage);
                searchController.setPrevious(stage.getScene());
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int numberOfProducts() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource("numOfLinks.fxml")));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            finalStage = new Stage();
            finalStage.setScene(scene);
            finalStage.initModality(Modality.APPLICATION_MODAL);
            finalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Integer.parseInt(amount.getText());
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

    public void viewScene() {
        if (!Objects.isNull(settingsController.getCurrentUsername())) {
            currentUsername = settingsController.getCurrentUsername();
        }
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("viewSaved.fxml"));
        ViewController viewController = new ViewController();
        viewController.setUsername(currentUsername);
        loader.setController(viewController);
        try {
            ScrollPane root = loader.load();
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void settingsScene() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("settingsScene.fxml"));
        settingsController.setCurrentUsername(currentUsername);
        loader.setController(settingsController);
        try {
            BorderPane root = loader.load();
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingsController = new SettingsController();
        searchController = new SearchController();

        if (start != null) {
            start.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (!Utilities.isPositiveInt(amount.getText())) {
                        popUpWindow("Enter valid number");
                    } else {
                        finalStage.close();
                    }
                }
            });
        }
    }
}
