package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // https://www.geeksforgeeks.org/singleton-class-java/
    // static variable instance of type Singleton
    private static Database instance = null;

    // private constructor restricted to this class itself
    private Database() {}

    // static method to create instance of Singleton class
    public static Database getInstance()
    {
        if (instance == null)
            instance = new Database();

        return instance;
    }

    private Connection connection;

    public void connect() throws SQLException {
        String databasePath = "db";
        connection = DriverManager.getConnection(String.format("jdbc:derby:%s;create=true;derby.language.sequence.preallocator=1", databasePath));
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connect();
            return connection;
        } else if (connection.isClosed()) {
            connect();
            return connection;
        }
        return connection;
    }
}
