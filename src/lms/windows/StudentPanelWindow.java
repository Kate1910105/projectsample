package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import lms.Main;
import lms.models.User;

public class StudentPanelWindow {
    public static Scene getScene() throws Exception {
        return Main.loadScene("studentpanel");
    }

    @FXML
    private void initialize() {
        System.out.println("student panel init");
    }

    public void logout(ActionEvent event) throws Exception {
        User user = new User();
        user.logout();
    }

    public void listBooks(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListBookStudentsPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
