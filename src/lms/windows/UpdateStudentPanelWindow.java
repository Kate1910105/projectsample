package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

public class UpdateStudentPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("update student panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("update_student");
    }

    @FXML
    public void confirm(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
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

    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
