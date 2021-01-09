package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import lms.Main;
import lms.models.Book;
import lms.models.User;

public class ListBookPanelWindow {
    public TableView<Book> tableView;

    @FXML
    private TableView <Book> tableView;


    @FXML
    private void initialize() throws SQLException  {
        System.out.println("book list panel init");
        ArrayList<Book> books = Book.all();
        ArrayList<User> librarians = new ArrayList<>();



        TableColumn<User, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().add(idColumn);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableView.getColumns().add(usernameColumn);

        TableColumn<User, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tableView.getColumns().add(fullNameColumn);

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableView.getColumns().add(roleColumn);

        tableView.getItems().addAll(librarians);

        System.out.println(books);

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

    public void update(ActionEvent actionEvent) throws Exception {

    }

    public void listbook(ActionEvent actionEvent) throws Exception  {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }
}
