package lms.windows.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lms.Main;
import lms.models.User;
import lms.types.Role;

import java.time.LocalDateTime;

public class UpdateStudentPanelWindow {
    @FXML
    private CheckBox canBorrow;
    @FXML
    private TextField username;
    @FXML
    private TextField fullName;
    @FXML
    private PasswordField password;

    @FXML
    private void initialize() {
        User editingUser = Main.editingUser;
        username.setText(editingUser.fullName);
        fullName.setText(editingUser.fullName);
        password.setText(editingUser.password);
        canBorrow.setSelected(editingUser.canBorrow);
        System.out.println("update student panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("student/update");
    }

    @FXML
    public void confirm(ActionEvent actionEvent) throws Exception {
        User editingUser = Main.editingUser;
        editingUser.username = username.getText();
        editingUser.password = password.getText();
        editingUser.fullName = fullName.getText();
        editingUser.role = Role.Student;
        editingUser.createdAt = LocalDateTime.now();
        editingUser.isActive = true;
        editingUser.canBorrow = canBorrow.isSelected();
        editingUser.update();

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
