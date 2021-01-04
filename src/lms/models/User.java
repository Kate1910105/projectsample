package lms.models;

import lms.exceptions.authorization.AuthorizationError;
import lms.exceptions.authorization.InactiveUser;
import lms.exceptions.authorization.InvalidCredentials;
import lms.exceptions.authorization.UserNotFound;
import lms.types.Role;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
                \tID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),                
                \tUSERNAME VARCHAR(255) NOT NULL UNIQUE,
                \tPASSWORD VARCHAR(511) NOT NULL,
                \tFULL_NAME VARCHAR(255) NOT NULL,
                \tROLE INTEGER NOT NULL,
                \tCAN_BORROW INTEGER NOT NULL,
                \tIS_ACTIVE BOOLEAN NOT NULL,
                \tCREATED_AT TIMESTAMP NOT NULL
                )""";

        createTableRaw(tableSQL);
        System.out.println("APP.USERS table is created");
    }

    public void create() throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement("""
                INSERT INTO APP.USERS(USERNAME,PASSWORD,FULL_NAME,ROLE,CAN_BORROW,IS_ACTIVE,CREATED_AT)
                VALUES (?,?,?,?,?,?,?)
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

    public static User login(String username, String password) throws AuthorizationError {
        try {
            Connection connection = db.getConnection();

            PreparedStatement statement = connection.prepareStatement(String.format("""
                SELECT * FROM APP.USERS WHERE USERNAME = '%s'
            """, username));

            statement.execute();

            ResultSet result = statement.getResultSet();
            ArrayList<User> users = new ArrayList<>();

            while (result.next()) {
                User user = new User();
                user.id = result.getInt("id");
                user.username = result.getString("username");
                user.password = result.getString("password");
                user.fullName = result.getString("full_name");
                user.role = rawToRole(result.getInt("role"));
                user.canBorrow = result.getBoolean("can_borrow");
                user.isActive = result.getBoolean("is_active");
                user.createdAt = result.getTimestamp("created_at").toLocalDateTime();
                users.add(user);
            }

            statement.close();
            connection.close();

            if (users.size() == 0) {
                throw new UserNotFound(String.format("User with login %s is not found", username));
            }

            User user = users.get(0);

            if (!user.password.equals(password)) {
                throw new InvalidCredentials(String.format("Password of given user %s is wrong", username));
            }

            if (!user.isActive) {
                throw new InactiveUser(String.format("User %s is not active", username));
            }

            return user;
        } catch (SQLException error) {
            throw new AuthorizationError("Unknown");
        }
    }
}
