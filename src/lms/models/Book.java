package lms.models;

import lms.types.BookStatus;
import lms.types.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Book extends Model {
    public int id;
    public String ISBN;
    public String title;
    public String subject;
    public String author;
    public BookStatus status;
    public LocalDateTime publishDate;
    public LocalDateTime createdAt;

    public static void createTable() throws SQLException {
        String tableSQL = "CREATE TABLE APP.BOOKS(ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),TITLE VARCHAR(511) NOT NULL,SUBJECT VARCHAR(255) NOT NULL,AUTHOR VARCHAR(255) NOT NULL,ISBN VARCHAR(13) NOT NULL,PUBLISH_DATE DATE NOT NULL,STATUS INTEGER NOT NULL,CREATED_AT TIMESTAMP NOT NULL)";

        createTableRaw(tableSQL);
        System.out.println("APP.BOOKS table is created");
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
