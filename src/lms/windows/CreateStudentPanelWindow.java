package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lms.Database;
import lms.Main;
import lms.models.User;
import lms.types.Role;

import java.time.LocalDateTime;


public class CreateStudentPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("create student panel init");
    }

    @FXML
    private TextField username;
    @FXML
    private TextField fullName;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox canBorrow;


    public static Scene getScene() throws Exception {
        return Main.loadScene("create_student");
    }
    @FXML
    public void confirm(ActionEvent actionEvent) throws Exception {
        User user = new User();
        user.username = username.getText();
        user.password = password.getText();
        user.fullName = fullName.getText();
        user.role = Role.Student;
        user.createdAt = LocalDateTime.now();
        user.isActive = true;
        user.create();

        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void addstudent(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void liststudent(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
