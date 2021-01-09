package lms.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import lms.Main;
import lms.exceptions.authorization.UserNotFound;
import lms.models.User;

import java.sql.SQLException;

public class ListStudentPanelWindow {

      public static Scene getScene() throws Exception {
        return Main.loadScene("list_student");
    }



    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        System.out.println(Main.currentUser.role);
        switch (Main.currentUser.role) {
            case Administrator:
                scene = AdminPanelWindow.getScene();
                break;
            case Librarian:
                scene = LibrarianPanelWindow.getScene();
                break;
            default:
                scene = StudentPanelWindow.getScene();
                break;

        }
        System.out.println(Main.currentUser.role);

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
    public void delete(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = DeleteStudentPanelWindow.getScene();
        Main.window.setScene(scene);

    }
    @FXML
    public void update(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = UpdateStudentPanelWindow.getScene();
        Main.window.setScene(scene);
    }

}
