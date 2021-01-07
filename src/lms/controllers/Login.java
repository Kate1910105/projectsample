package lms.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lms.exceptions.authorization.AuthorizationError;
import lms.models.User;


public class Login {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void initialize() {
        System.out.println("login init");
    }

    public void login(ActionEvent event) {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());
        try {
            User user = User.login(username.getText(), password.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Logged in");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Welcome, %s!", user.fullName));
            alert.showAndWait();

        } catch (AuthorizationError error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authorization error");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }
}
