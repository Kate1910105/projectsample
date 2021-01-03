package lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lms.types.Role;
import lms.models.User;

import java.time.LocalDateTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lms.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        Database.getInstance().connect();
        User.createTable();
        User user = new User();
        user.username = "admins";
        user.password = "password";
        user.fullName = "John Adams";
        user.role = Role.Administrator;
        user.createdAt = LocalDateTime.now();
        user.create();

        System.out.println("Connected");
        launch(args);
    }
}
