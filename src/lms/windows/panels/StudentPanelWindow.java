package lms.windows.panels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;
import lms.models.User;
import lms.windows.ListBookStudentsPanelWindow;

public class StudentPanelWindow {
    public static Scene getScene() throws Exception {
        return Main.loadScene("panels/student");
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
