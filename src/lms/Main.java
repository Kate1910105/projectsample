package lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lms.models.Book;
import lms.models.Loan;
import lms.models.User;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import lms.types.Role;

public class Main extends Application {
    Stage window;
    Scene loginScene;
    Scene adminPanel;
    Scene studentPanel;
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        // Initialize all scenes
        loginScene = new Scene(loadScene("login"), 800, 600);
        adminPanel = new Scene(loadScene("adminpanel"), 800, 600);
        studentPanel = new Scene(loadScene("studentpanel"), 800, 600);

        window.setTitle("Library Management System");
        window.setScene(loginScene);
        window.show();

        // Loading
    }

    public Parent loadScene(String name) throws IOException {
        return FXMLLoader.load(getClass().getResource(String.format("screens/%s.fxml", name)));
    }

    public static void main(String[] args) throws Exception {
        Database.getInstance().connect();
        User.createTable();
        Book.createTable();
        Loan.createTable();

        User user = new User();
        user.username = "irda";
        user.password = "password";
        user.fullName = "Irda";
        user.role = Role.Administrator;
        user.createdAt = LocalDateTime.now();
        user.isActive = true;
        user.create();



        ArrayList<User> users = User.all();
        System.out.println(users);

//      System.out.println("Connected");
        launch(args);
    }
}
