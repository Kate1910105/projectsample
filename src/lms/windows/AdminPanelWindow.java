package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class AdminPanelWindow {
    public static Scene getScene() throws Exception {
        return Main.loadScene("adminpanel");
    }

    @FXML
    private void initialize() {
        System.out.println("admin panel init");
    }

    public void showStudent(ActionEvent event) {

    }
    public void showBook(ActionEvent event) {

    }
    public void showLibrarian(ActionEvent event) {

    }
}
