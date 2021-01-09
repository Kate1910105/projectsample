package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;
import lms.models.User;

public class UpdateLibrarianPanelWindow {
    @FXML
    private void initialize() {
        User editingUser = Main.editingUser;
        System.out.println(editingUser.username);
        System.out.println("update librarian panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("update_librarian");
    }

    @FXML
    public void confirm(ActionEvent actionEvent) throws Exception {

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
