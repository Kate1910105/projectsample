package lms.models;

import lms.exceptions.model.ModelError;
import lms.exceptions.model.RecordNotFound;
import lms.types.BookStatus;
import lms.types.Role;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Book extends Model {
    public int id;
    public String ISBN;
    public String title;
    public String subject;
    public String author;
    public BookStatus status;
    public LocalDateTime createdAt;
    public LocalDateTime publishDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void createTable() throws SQLException {
        String tableSQL = "CREATE TABLE APP.BOOKS(ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),TITLE VARCHAR(511) NOT NULL,SUBJECT VARCHAR(255) NOT NULL,AUTHOR VARCHAR(255) NOT NULL,ISBN VARCHAR(13) NOT NULL,PUBLISH_DATE DATE NOT NULL,STATUS INTEGER NOT NULL,CREATED_AT TIMESTAMP NOT NULL)";

        createTableRaw(tableSQL);
        System.out.println("APP.BOOKS table is created");
    }

    public int getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getAuthor() {
        return author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt.format(formatter);
    }

    public String getPublishDate() throws  NullPointerException {
        return publishDate.format(formatter);
    }

    public void create() throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO APP.BOOKS(ISBN,TITLE,SUBJECT,AUTHOR,STATUS,PUBLISH_DATE,CREATED_AT) VALUES (?,?,?,?,?,?,?)");
        statement.setString(1, ISBN);
        statement.setString(2, title);
        statement.setString(3, subject);
        statement.setString(4, author);
        statement.setInt(5, statusToRaw(status));
        statement.setTimestamp(6, Timestamp.valueOf(publishDate));
        statement.setTimestamp(7, Timestamp.valueOf(createdAt));

        try {
            statement.executeUpdate();
            System.out.printf("book %s is created\n", title);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.printf("book %s already exists\n", title);
            }
        }
    }

    public static Book fetch(int id) throws ModelError, SQLException {
        Connection connection = db.getConnection();

        String query = "SELECT * FROM APP.BOOKS WHERE ID=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        statement.execute();

        ResultSet result = statement.getResultSet();
        ArrayList<Book> books = new ArrayList<>();

        while (result.next()) {
            Book book = serializeBookFromResult(result);
            books.add(book);
        }

        statement.close();
        connection.close();

        if (books.size() == 0) {
            throw new RecordNotFound(String.format("Book with id %d is not found", id));
        } else {
            return books.get(0);
        }
    }

    public static ArrayList<Book> all() throws SQLException {
        Connection connection = db.getConnection();

        String query = "SELECT * FROM APP.BOOKS";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.execute();

        ResultSet result = statement.getResultSet();
        ArrayList<Book> books = new ArrayList<>();

        while (result.next()) {
            Book book = serializeBookFromResult(result);
            books.add(book);
        }

        statement.close();
        connection.close();

        return books;
    }

    public static Book serializeBookFromResult(ResultSet result) {
        Book book = new Book();
        try {
            book.id = result.getInt("id");
            book.ISBN = result.getString("ISBN");
            book.title = result.getString("title");
            book.subject = result.getString("subject");
            book.author = result.getString("author");
            book.status = rawToStatus(result.getInt("status"));
            book.createdAt = result.getTimestamp("created_at").toLocalDateTime();
            book.publishDate = result.getTimestamp("publish").toLocalDateTime();
            return book;
        } catch (SQLException e) {
            return book;
        }
    }

    public void update() throws SQLException {
        Connection connection = db.getConnection();
        String query = "UPDATE APP.BOOKS\n" +
                "SET ISBN=?,\n" +
                "TITLE=?,\n" +
                "SUBJECT=?,\n" +
                "AUTHOR=?,\n" +
                "STATUS=?,\n" +
                "PUBLISH_DATE=?,\n" +
                "CREATED_AT=?\n" +
                "WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, ISBN);
        statement.setString(2, title);
        statement.setString(3, subject);
        statement.setString(4, author);
        statement.setInt(5, statusToRaw(status));
        statement.setTimestamp(6, Timestamp.valueOf(publishDate));
        statement.setTimestamp(7, Timestamp.valueOf(createdAt));
        statement.setInt(8, id);

        try {
            statement.executeUpdate();
            System.out.printf("book %s is updated\n", title);
        } catch (SQLException e) {
            e.printStackTrace();
//            if (e.getSQLState().equals("23505")) {
//                System.out.printf("user %s is already exists\n", username);
//            }
        }

        statement.close();
        connection.close();
    }

    public void delete() throws SQLException {
        Connection connection = db.getConnection();
        String query = "DELETE FROM APP.BOOKS WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.printf("books %s is updated\n", title);
        } catch (SQLException e) {
            e.printStackTrace();
//            if (e.getSQLState().equals("23505")) {
//                System.out.printf("user %s is already exists\n", username);
//            }
        }

        statement.close();
        connection.close();
    }

    // Convert BookStatus enum to raw format (to store in db)
    public static int statusToRaw(BookStatus status) {
        switch (status) {
            case Available:
                return 1;
            case Borrowed:
                return 2;
            default:
                return 3;
        }
    }

    // Convert raw to BookStatus enum (to use in the code)
    public static BookStatus rawToStatus(int status) {
        switch (status) {
            case 1:
                return BookStatus.Available;
            case 2:
                return BookStatus.Borrowed;
            default:
                return BookStatus.Reserved;
        }
    }
}
