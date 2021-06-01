package sample.infrastructure.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite../../../../db/ticktask.db");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
