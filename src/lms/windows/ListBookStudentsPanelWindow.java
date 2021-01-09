package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import lms.Main;

public class ListBookStudentsPanelWindow {
    public TableView tableView;

    @FXML
    private void initialize() {
        System.out.println("book list student panel init");
    }


    public static Scene getScene() throws Exception {
        return Main.loadScene("list_books_students");
    }

    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = StudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    @FXML
    public void listBook(ActionEvent actionEvent) throws Exception  {
        Scene scene;
        scene = ListBookStudentsPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
