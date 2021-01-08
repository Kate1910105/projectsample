package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import lms.Main;

public class AdminPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("admin panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("adminpanel");
    }


    @FXML
    public void showStudent(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void showBook(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);

    }

    public void showLibrarian(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);

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
