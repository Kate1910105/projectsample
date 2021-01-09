package lms.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lms.Main;
import lms.models.Book;
import lms.types.BookStatus;

import java.time.LocalDate;

public class UpdateBookPanelWindow {
    public TextField title;
    public TextField subject;
    public TextField author;
    public TextField isbn;
    public DatePicker created;
    public DatePicker publish;
    public ComboBox status;

    @FXML
    private void initialize() {
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
        Book book = new Book();
        book.title = title.getText();
        book.subject = subject.getText();
        book.author = author.getText();
        book.ISBN = isbn.getText();

        LocalDate dateCreated = created.getValue();
        book.createdAt = dateCreated.atStartOfDay();

        LocalDate datePublish = publish.getValue();
        book.publishDate = datePublish.atStartOfDay();

        if (status.getValue().equals("Available")) {
            book.status = BookStatus.Available;
        } else if (status.getValue().equals("Borrowed")) {
            book.status = BookStatus.Borrowed;
        } else if (status.getValue().equals("Reserved")) {
            book.status = BookStatus.Reserved;
        } else book.status = BookStatus.Available;

        book.update();

        Scene scene;
        scene = ListBookPanelWindow.getScene();
        Main.window.setScene(scene);
    }

}
