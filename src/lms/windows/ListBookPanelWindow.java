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

    public void addbook(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void delete(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = DeleteBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void update(ActionEvent actionEvent) throws Exception  {
        Scene scene;
        scene = UpdateBookPanelWindow.getScene();
        Main.window.setScene(scene);

    }

    public void listbook(ActionEvent actionEvent) throws Exception  {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
