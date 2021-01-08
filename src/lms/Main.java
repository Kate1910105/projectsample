package lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lms.windows.LoginWindow;
import lms.models.Book;
import lms.models.Loan;
import lms.models.User;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.time.LocalDateTime;
import lms.types.Role;

public class Main extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Library Management System");
        window.setScene(LoginWindow.getScene());
        window.show();
    }

    public static Scene loadScene(String name) throws Exception {
        String path = String.format("src/lms/screens/%s.fxml", name);
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        FXMLLoader loader = new FXMLLoader();
        Parent parent = loader.load(fileInputStream);
        return new Scene(parent, 800, 600);
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
