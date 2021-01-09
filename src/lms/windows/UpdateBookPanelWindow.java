package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class UpdateBookPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("update book panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("update_book");
    }



    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void addbook(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void listbook(ActionEvent actionEvent) throws Exception  {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void confirm(ActionEvent actionEvent) throws Exception {

    }

}
