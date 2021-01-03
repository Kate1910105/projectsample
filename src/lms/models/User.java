package lms.models;

import lms.types.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User extends Model {

    public int id;
    public String username;
    public String password;
    public String fullName;
    public Role role = Role.Student;
    public boolean canBorrow = true;
    public boolean isActive = true;
    public LocalDateTime createdAt;

    // Convert Role enum to raw format
    public static int roleToRaw(Role role) {
        return switch (role) {
            case Administrator -> 1;
            case Librarian -> 2;
            default -> 3;
        };
    }

    // Convert raw to Role enum
    public static Role rawToRole(int role) {
        return switch (role) {
            case 1 -> Role.Administrator;
            case 2 -> Role.Librarian;
            default -> Role.Student;
        };
    }

    public static void createTable() throws SQLException {
        String tableSQL = """
                CREATE TABLE APP.USERS(
                \tid INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),                
                \tpassword VARCHAR(511) NOT NULL,
                \tusername VARCHAR(255) NOT NULL UNIQUE,
                \tfull_name VARCHAR(255) NOT NULL,
                \trole INTEGER NOT NULL,
                \tcan_borrow INTEGER NOT NULL,
                \tis_active BOOLEAN NOT NULL,
                \tcreated_at TIMESTAMP NOT NULL
                )""";

        createTableRaw(tableSQL);
        System.out.println("APP.USERS table is created");
    }

    public void create() throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement("""
        insert into APP.USERS(username,password,full_name,role,can_borrow,is_active,created_at)
        values (?,?,?,?,?,?,?)
        """);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, fullName);
        statement.setInt(4, roleToRaw(role));
        statement.setBoolean(5, canBorrow);
        statement.setBoolean(6, isActive);
        statement.setTimestamp(7, Timestamp.valueOf(createdAt));

        try {
            statement.executeUpdate();
            System.out.printf("user %s is created\n", username);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.printf("user %s is already exists\n", username);
            }
        }

        statement.close();
        connection.close();
    }
}
