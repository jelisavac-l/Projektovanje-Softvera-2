package db;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection conn;

    private DatabaseConnection() {
        
    }

    public static Connection getConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("DB_URL") + " " + dotenv.get("DB_USER"));
        if(conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(dotenv.get("DB_URL"), 
                    dotenv.get("DB_USER"),
                    dotenv.get("DB_PASS"));
        }

        return conn;
    }


}
