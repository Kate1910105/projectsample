package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import lms.Main;
import lms.models.User;

import static lms.types.Role.Administrator;
import static lms.types.Role.Librarian;

public class ListLibrarianPanelWindow {
    @FXML
    private void initialize() {
        System.out.println("librarian list panel init");
    }


    public static Scene getScene() throws Exception {
        return Main.loadScene("list_librarian");
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
    public void delete(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = DeleteLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);

    }
    @FXML
    public void update(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = UpdateLibrarianPanelWindow.getScene();
        Main.window.setScene(scene);
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

}
