package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import lms.Main;

public class StudentPanelWindow {
    public static Scene getScene() throws Exception {
        return Main.loadScene("studentpanel");
    }

    @FXML
    private void initialize() {
        System.out.println("student panel init");
    }

    public void logout(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Goodbye!"));
        alert.showAndWait();
        Scene scene;
        scene = LoginWindow.getScene();
        Main.window.setScene(scene);
    }
}
