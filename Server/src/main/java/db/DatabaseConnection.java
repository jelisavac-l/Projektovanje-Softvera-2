package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Move to properties
    private static final String DB = "jdbc:mysql://localhost:3306/ProjektovanjeSoftvera2";
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection conn;

    private DatabaseConnection() {

    }

    public static Connection getConnection() throws SQLException {
        if(conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB, USER, PASS);
        }

        return conn;
    }


}
