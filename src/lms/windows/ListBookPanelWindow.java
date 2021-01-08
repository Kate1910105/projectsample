package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class ListBookPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("book list panel init");
    }


    public static Scene getScene() throws Exception {
        return Main.loadScene("list_books");
    }

    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = AdminPanelWindow.getScene();
        Main.window.setScene(scene);
    }

}
