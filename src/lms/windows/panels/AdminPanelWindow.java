package lms.windows.panels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;
import lms.models.User;
import lms.windows.book.ListBookPanelWindow;
import lms.windows.librarian.ListLibrarianPanelWindow;
import lms.windows.student.ListStudentPanelWindow;

//scenes for administrators
public class AdminPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("admin panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("panels/admin");
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
            User user = new User();
            user.logout();
    }
}
