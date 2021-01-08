package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class StudentPanelWindow {
    public static Scene getScene() throws Exception {
        return Main.loadScene("studentpanel");
    }

    @FXML
    private void initialize() {
        System.out.println("student panel init");
    }
}
