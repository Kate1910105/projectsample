package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class LibrarianPanelWindow {
    public static Scene getScene() throws Exception {
        return Main.loadScene("librarianpanel");
    }

    @FXML
    private void initialize() {
        System.out.println("librarian panel init");
    }

    public void showStudent(ActionEvent event) {

    }
    public void showBook(ActionEvent event) {

    }
}
