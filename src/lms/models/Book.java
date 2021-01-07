package lms.models;

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
    public LocalDateTime publishDate;
    public LocalDateTime createdAt;

    public static void createTable() throws SQLException {
        String tableSQL = "CREATE TABLE APP.BOOK(ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),TITLE VARCHAR(511) NOT NULL,SUBJECT VARCHAR(255) NOT NULL,AUTHOR VARCHAR(255) NOT NULL,ISBN VARCHAR(13) NOT NULL,PUBLISH_DATE DATE NOT NULL,STATUS INTEGER NOT NULL,CREATED_AT TIMESTAMP NOT NULL)";

        createTableRaw(tableSQL);
        System.out.println("APP.BOOKS table is created");
    }

    public void create() throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO APP.USERS(ID,ISBN,TITLE,SUBJECT,AUTHOR,PUBLISH_DATE,CREATED_AT) VALUES (?,?,?,?,?,?,?)");
        statement.setInt(1, id);
        statement.setString(2, ISBN);
        statement.setString(3, title);
        statement.setString(4, subject);
        statement.setString(5, author);
        statement.setTimestamp(7, Timestamp.valueOf(publishDate));
        statement.setTimestamp(7, Timestamp.valueOf(createdAt));

        try {
            statement.executeUpdate();
            System.out.printf("book %s is created\n", title);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.printf("book %s is already exists\n", title);
            }
        }
    }
}
