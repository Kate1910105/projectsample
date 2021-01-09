package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import lms.Main;

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
        scene = AdminPanelWindow.getScene();
        Main.window.setScene(scene);
    }

}