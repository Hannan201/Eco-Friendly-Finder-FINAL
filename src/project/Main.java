package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.InputStream;
import java.util.Objects;

/***
 * Problem list:
 * - Jsoup library is not here( Solved)
 * - Program is not robust (Basically solved)
 * - Import friccing JavaFX (Done)
 * - Do stuff with friccing JAVAFX
 */

public class Main extends Application {

    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 670;

    public static void main(String[] args) {
        launch(args);
        //Welcome screen
        int select;
        //Mouse click on whatever sets select to 1, 2, or 3
//        while(!select == 3) {
//            if (select == 1) {
//                //logging in, display 2 bars for username and password, set password characters to dots
//                //If incorrect, loop with message telling username/password is wrong
//                //If correct, then interface screen
//            } else if (select == 2) {
//                //Create account screen, 2 spaces for user's username and password(dots), which are used in createAccount()
//            }
//        }
    }

    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("exit.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.init(loader, root);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 0, 0, 0));

        HBox hBox = new HBox(95);
        hBox.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane();

        Label label = new Label("Welcome to the Eco-Friendly\n" +
                "Alternative Finder!");
        label.setAlignment(Pos.CENTER);

        Rectangle welcomeBackground = new Rectangle(650, 160);
        welcomeBackground.setOpacity(0.5);

        stackPane.getChildren().addAll(welcomeBackground, label);

        borderPane.setTop(stackPane);

        Button loginButton = new Button("Log in");
        hBox.getChildren().add(loginButton);

        Button creatAccountButton = new Button("Create Account");
        hBox.getChildren().add(creatAccountButton);

        Button leaveButton = new Button("Leave");
        hBox.getChildren().add(leaveButton);

        borderPane.setCenter(hBox);

        Scene scene = new Scene(borderPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("styleOne.css")).toExternalForm());
        borderPane.setId("homeBackground");
        borderPane.requestFocus();

        InputStream tempIcon = getClass().getResourceAsStream("/images/programIcon.png");

        if (tempIcon != null) {
            Image icon = new Image(tempIcon);
            primaryStage.getIcons().add(icon);
        }

        primaryStage.setTitle("Eco Friendly Finder");

        primaryStage.setScene(scene);
        primaryStage.show();

        leaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.exitPrompt();
                if (controller.getStatus()) {
                    primaryStage.close();
                }
            }
        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                controller.exitPrompt();
                if (controller.getStatus()) {
                    primaryStage.close();
                }
            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loginLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("login.fxml")));
                    BorderPane root = loginLoader.load();
                    LoginController loginController = loginLoader.getController();
                    loginController.setHomeScene(scene);
                    Scene loginScene = new Scene(root);
                    root.requestFocus();
                    loginScene.getStylesheets().add(this.getClass().getResource("loginStyle.css").toExternalForm());
                    root.setId("homeBackground");
                    primaryStage.setScene(loginScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        creatAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader signupLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("signup.fxml")));
                    BorderPane root = signupLoader.load();
                    SignupController signupController = signupLoader.getController();
                    signupController.setHomeScene(scene);
                    Scene loginScene = new Scene(root);
                    root.requestFocus();
                    loginScene.getStylesheets().add(this.getClass().getResource("loginStyle.css").toExternalForm());
                    root.setId("homeBackground");
                    primaryStage.setScene(loginScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}