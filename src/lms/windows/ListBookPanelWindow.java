package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lms.Main;
import lms.models.Book;
import lms.models.User;
import lms.types.Role;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListBookPanelWindow {
    @FXML
    private TableView <Book> tableView;


    @FXML
    private void initialize() throws SQLException  {
        System.out.println("book list panel init");
        ArrayList<Book> books = Book.all();

        TableColumn<Book, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().add(idColumn);

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        tableView.getColumns().add(isbnColumn);

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableView.getColumns().add(titleColumn);

        TableColumn<Book, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        tableView.getColumns().add(subjectColumn);

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableView.getColumns().add(authorColumn);

        TableColumn<Book, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.getColumns().add(statusColumn);

        TableColumn<Book, String> createdAtColumn = new TableColumn<>("Created At");
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tableView.getColumns().add(createdAtColumn);

   /*     TableColumn<Book, String> publishDateColumn = new TableColumn<>("Publish Date");
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        tableView.getColumns().add(publishDateColumn); */

        tableView.getItems().addAll(books);

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
