package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lms.Main;
import lms.exceptions.authorization.AuthorizationError;
import lms.models.User;


public class LoginWindow {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public static Scene getScene() throws Exception {
        return Main.loadScene("login");
    }

    @FXML
    private void initialize() {
        System.out.println("login init");
    }

    public void login(ActionEvent event) throws Exception {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());
        try {
            User user = User.login(username.getText(), password.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Logged in");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Welcome, %s!", user.fullName));
            alert.showAndWait();
            Scene scene;

            switch (user.role) {
                case Administrator:
                    scene = AdminPanelWindow.getScene();
                    break;
                case Librarian:
                    scene = LibrarianPanelWindow.getScene();
                    break;
                default:
                    scene = StudentPanelWindow.getScene();
                    break;
            }

            Main.window.setScene(scene);
        } catch (AuthorizationError error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authorization error");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }
}
