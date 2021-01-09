package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lms.Main;
import lms.models.Book;
import lms.models.User;
import lms.types.BookStatus;
import lms.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateBookPanelWindow {
    public TextField isbn;
    public TextField title;
    public TextField subject;
    public TextField author;
    public ComboBox status;
    public DatePicker created;
    public DatePicker publish;

    public String Available;
    public String Borrowed;
    public String Reserved;

    @FXML
    private void initialize() {
        Book editingBook = Main.editingBook;

        isbn.setText(editingBook.ISBN);
        title.setText(editingBook.title);
        subject.setText(editingBook.subject);
        author.setText(editingBook.author);

        if (editingBook.status == BookStatus.Available) {
            status.setValue(Available);
        } else if (editingBook.status == BookStatus.Borrowed) {
            status.setValue(Borrowed);
        } else if (editingBook.status == BookStatus.Reserved) {
            status.setValue(Reserved);
        }

        created.setValue(editingBook.createdAt.toLocalDate());
     /*   publish.setValue(editingBook.publishDate.toLocalDate());  */

        System.out.println("update book panel init");
    }

    public static Scene getScene() throws Exception {
        return Main.loadScene("update_book");
    }


    @FXML
    public void back(ActionEvent event) throws Exception {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void addbook(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = CreateBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void listbook(ActionEvent actionEvent) throws Exception {
        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

    public void confirm(ActionEvent actionEvent) throws Exception {
        Book editingBook = Main.editingBook;

        editingBook.ISBN = isbn.getText();
        editingBook.title = title.getText();
        editingBook.subject = subject.getText();
        editingBook.author = author.getText();
        if (status.getValue() == Available) {
            editingBook.status = BookStatus.Available;
        } else if (status.getValue() == Borrowed) {
            editingBook.status = BookStatus.Borrowed;
        } else if (status.getValue() == Reserved) {
            editingBook.status = BookStatus.Reserved;
        }

        LocalDate dateCreated = created.getValue();
        editingBook.createdAt = dateCreated.atStartOfDay();

        LocalDate datePublish = publish.getValue();
        editingBook.publishDate = datePublish.atStartOfDay();

        editingBook.update();

        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

}
