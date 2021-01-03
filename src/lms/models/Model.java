package lms.models;

import lms.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
    // Make available db everywhere
    public static Database db = Database.getInstance();

    protected static void createTableRaw(String tableSQL) throws SQLException {
        Connection connection = db.getConnection();
        Statement statement = connection.createStatement();

        try {
            statement.executeUpdate(tableSQL);
        } catch (SQLException e) {
            // skip if exists
            // https://db.apache.org/derby/docs/10.2/ref/rrefexcept71493.html
            if (!e.getSQLState().equals("X0Y32")) {
                throw e;
            }
        }

        statement.close();
        connection.close();
    }
}
