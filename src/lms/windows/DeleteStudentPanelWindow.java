package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class DeleteStudentPanelWindow {
    @FXML
    public void addstudent(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
    @FXML
    public void liststudent(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
