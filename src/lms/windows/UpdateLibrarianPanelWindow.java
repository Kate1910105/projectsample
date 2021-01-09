package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lms.Main;
import lms.models.User;
import lms.types.Role;

import java.time.LocalDateTime;

public class UpdateLibrarianPanelWindow {

    @FXML
    private TextField username;
    @FXML
    private TextField fullName;
    @FXML
    private PasswordField password;

    @FXML
    private void initialize() {
        User editingUser = Main.editingUser;
        username.setText(editingUser.username);
        fullName.setText(editingUser.fullName);
        password.setText(editingUser.password);
        System.out.println(editingUser.username);
        System.out.println("update librarian panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("update_librarian");
    }

    @FXML
    public void confirm(ActionEvent actionEvent) throws Exception {
        User editingUser = Main.editingUser;
        editingUser.username = username.getText();
        editingUser.password = password.getText();
        editingUser.fullName = fullName.getText();
        editingUser.role = Role.Librarian;
        editingUser.createdAt = LocalDateTime.now();
        editingUser.isActive = true;
        editingUser.update();

        Scene scene;
        scene = ListLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void addlibrarian(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void listlibrarian(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
