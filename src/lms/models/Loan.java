package lms.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Loan extends Model {
    public int id;
    public int userID;
    public int bookID;
    public LocalDateTime borrowedDate;
    public LocalDateTime deadlineAt;
    public LocalDateTime returnedAt;

    public static void createTable() throws SQLException {
        String tableSQL = "CREATE TABLE APP.LOAN(ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),USER_ID INTEGER NOT NULL,BOOK_ID INTEGER NOT NULL,BORROW_DATE DATE NOT NULL,RETURN_DATE DATE NOT NULL,DEADLINE_DATE DATE NOT NULL)";

        createTableRaw(tableSQL);
        System.out.println("APP.LOANS table is created");
    }

    public void create() throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO APP.LOANS(USER_ID,BOOK_ID,BORROWED_DATE,DEADLINE_AT,RETURNED_DATE) VALUES (?,?,?,?,?)");
        statement.setInt(2, userID);
        statement.setInt(3, bookID);
        statement.setTimestamp(7, Timestamp.valueOf(borrowedDate));
        statement.setTimestamp(7, Timestamp.valueOf(deadlineAt));
        statement.setTimestamp(7, Timestamp.valueOf(returnedAt));

        try {
            statement.executeUpdate();
            System.out.printf("loan %d is created\n", id);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.printf("loan %d is already exists\n", id);
            }
        }
    }

    public void update() throws SQLException {
        Connection connection = db.getConnection();
        String query = "UPDATE APP.LOANS\n" +
                "SET USER_ID=?,\n" +
                "BOOK_ID=?,\n" +
                "BORROWED_DATE=?,\n" +
                "DEADLINE_AT=?,\n" +
                "RETURNED_AT=?,\n" +
                "WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userID);
        statement.setInt(2, bookID);
        statement.setTimestamp(7, Timestamp.valueOf(borrowedDate));
        statement.setTimestamp(7, Timestamp.valueOf(deadlineAt));
        statement.setTimestamp(7, Timestamp.valueOf(returnedAt));
        statement.setInt(7, id);

        try {
            statement.executeUpdate();
            System.out.printf("loan %d is updated\n", id);
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
        String query = "DELETE FROM APP.LOANS WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.printf("loan %d is updated\n", id);
        } catch (SQLException e) {
            e.printStackTrace();
//            if (e.getSQLState().equals("23505")) {
//                System.out.printf("user %s is already exists\n", username);
//            }
        }

        statement.close();
        connection.close();
    }
}
