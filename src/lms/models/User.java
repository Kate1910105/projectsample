package lms.models;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import lms.Main;
import lms.exceptions.authorization.AuthorizationError;
import lms.exceptions.authorization.InactiveUser;
import lms.exceptions.authorization.InvalidCredentials;
import lms.exceptions.authorization.UserNotFound;
import lms.exceptions.model.ModelError;
import lms.exceptions.model.RecordNotFound;
import lms.types.Role;
import lms.windows.LoginWindow;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

// user class used to represent all roles
public class User extends Model {

    public int id;
    public String username;
    public String password;
    public String fullName;
    public Role role = Role.Student;
    public boolean canBorrow = true;
    public boolean isActive = true;
    public LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getcanBorrow() {
        return canBorrow;
    }


    public String getFullName() {
        return fullName;
    }

    public Role getRole() {
        return role;
    }

    // Convert Role enum to raw format (to store in db)
    public static int roleToRaw(Role role) {
        switch (role) {
            case Administrator:
                return 1;
            case Librarian:
                return 2;
            default:
                return 3;
        }
    }

    // Convert raw to Role enum (to use in the code)
    public static Role rawToRole(int role) {
        switch (role) {
            case 1:
                return Role.Administrator;
            case 2:
                return Role.Librarian;
            default:
                return Role.Student;
        }
    }

    public static void createTable() throws SQLException {
        String tableSQL = "CREATE TABLE APP.USERS(\tID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\tUSERNAME VARCHAR(255) NOT NULL UNIQUE,\tPASSWORD VARCHAR(511) NOT NULL,\tFULL_NAME VARCHAR(255) NOT NULL,\tROLE INTEGER NOT NULL,\tCAN_BORROW BOOLEAN NOT NULL,\tIS_ACTIVE BOOLEAN NOT NULL,\tCREATED_AT TIMESTAMP NOT NULL)";

        createTableRaw(tableSQL);
        System.out.println("APP.USERS table is created");
    }

    public void create() throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO APP.USERS(USERNAME,PASSWORD,FULL_NAME,ROLE,CAN_BORROW,IS_ACTIVE,CREATED_AT) " +
                        "VALUES (?,?,?,?,?,?,?)");
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

    public static User fetch(int id) throws ModelError, SQLException {
        Connection connection = db.getConnection();

        String query = "SELECT * FROM APP.USERS WHERE ID=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        statement.execute();

        ResultSet result = statement.getResultSet();
        ArrayList<User> users = new ArrayList<>();

        while (result.next()) {
            User user = serializeUserFromResult(result);
            users.add(user);
        }

        statement.close();
        connection.close();

        if (users.size() == 0) {
            throw new RecordNotFound(String.format("User with id %d is not found", id));
        } else {
            return users.get(0);
        }
    }

    // returns a list of users
    public static ArrayList<User> all() throws SQLException {
        Connection connection = db.getConnection();

        String query = "SELECT * FROM APP.USERS";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.execute();

        ResultSet result = statement.getResultSet();
        ArrayList<User> users = new ArrayList<>();

        while (result.next()) {
            User user = serializeUserFromResult(result);
            users.add(user);
        }

        statement.close();
        connection.close();

        return users;
    }

    // method to modify users
    public void update() throws SQLException {
        Connection connection = db.getConnection();
        String query = "UPDATE APP.USERS\n" +
                "SET PASSWORD=?,\n" +
                "FULL_NAME=?,\n" +
                "\"ROLE\"=?,\n" +
                "CAN_BORROW=?,\n" +
                "IS_ACTIVE=?,\n" +
                "CREATED_AT=?\n" +
                "WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, password);
        statement.setString(2, fullName);
        statement.setInt(3, roleToRaw(role));
        statement.setBoolean(4, canBorrow);
        statement.setBoolean(5, isActive);
        statement.setTimestamp(6, Timestamp.valueOf(createdAt));
        statement.setInt(7, id);

        try {
            statement.executeUpdate();
            System.out.printf("user %s is updated\n", username);
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
        String query = "DELETE FROM APP.USERS WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.printf("user %s is updated\n", username);
        } catch (SQLException e) {
            e.printStackTrace();
//            if (e.getSQLState().equals("23505")) {
//                System.out.printf("user %s is already exists\n", username);
//            }
        }

        statement.close();
        connection.close();
    }

    public static User serializeUserFromResult(ResultSet result) {
        User user = new User();
        try {
            user.id = result.getInt("id");
            user.username = result.getString("username");
            user.password = result.getString("password");
            user.fullName = result.getString("full_name");
            user.role = rawToRole(result.getInt("role"));
            user.canBorrow = result.getBoolean("can_borrow");
            user.isActive = result.getBoolean("is_active");
            user.createdAt = result.getTimestamp("created_at").toLocalDateTime();
            return user;
        } catch (SQLException e) {
            return user;
        }
    }

    public static User login(String username, String password) throws AuthorizationError {
        try {
            Connection connection = db.getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    String.format("SELECT * FROM APP.USERS WHERE USERNAME = '%s'", username)
            );

            statement.execute();

            ResultSet result = statement.getResultSet();
            ArrayList<User> users = new ArrayList<>();

            while (result.next()) {
                User user = serializeUserFromResult(result);
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
    public static void defaultAdmin() throws SQLException {
        String username = "admin";
        String password = "admin";

        try {
            User admin = login(username, password);
        } catch (AuthorizationError error) {
            User user = new User();
            user.username = username;
            user.password = password;
            user.fullName = "Admin";
            user.role = Role.Administrator;
            user.createdAt = LocalDateTime.now();
            user.isActive = true;
            user.create();

        }
    }
    public void logout() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Goodbye!"));
        alert.showAndWait();
        Scene scene;
        scene = LoginWindow.getScene();
        Main.window.setScene(scene);
    }

    public static void defaultLibrarian() throws SQLException {
        String username = "librarian";
        String password = "staff";

        try {
            User admin = login(username, password);
        } catch (AuthorizationError error) {
            User user = new User();
            user.username = username;
            user.password = password;
            user.fullName = "Librarian";
            user.role = Role.Librarian;
            user.createdAt = LocalDateTime.now();
            user.isActive = true;
            user.create();

        }
    }
    public static void defaultStudent() throws SQLException {
        String username = "student";
        String password = "student";

        try {
            User admin = login(username, password);
        } catch (AuthorizationError error) {
            User user = new User();
            user.username = username;
            user.password = password;
            user.fullName = "Student";
            user.role = Role.Student;
            user.createdAt = LocalDateTime.now();
            user.isActive = true;
            user.create();

        }
    }
}
