package lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lms.types.Role;
import lms.models.User;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main extends Application {
    Stage window;
    Scene loginScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        // Initialize all scenes
        loginScene = new Scene(loadScene("login"), 800, 600);

        window.setTitle("Library Management System");
        window.setScene(loginScene);
        window.show();

        // Loading
    }

    public Parent loadScene(String name) throws IOException {
        return FXMLLoader.load(getClass().getResource(String.format("screens/%s.fxml", name)));
    }

    public static void main(String[] args) throws Exception {
//        Database.getInstance().connect();
//        User.createTable();
//        User user = new User();
//        user.username = "admin4";
//        user.password = "password";
//        user.fullName = "John Adams";
//        user.role = Role.Administrator;
//        user.createdAt = LocalDateTime.now();
//        user.create();

//        System.out.println("Connected");
        launch(args);
    }
}
