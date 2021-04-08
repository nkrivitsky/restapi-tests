package db;

import framework.base.PropertiesResourceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private PropertiesResourceManager dbConnectionInfo = new PropertiesResourceManager("dbconnectioninfo");

    private static Connection conn;

    private DatabaseConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbConnectionInfo.getProperty("databaseUrl"),
                    dbConnectionInfo.getProperty("databaseLogin"),
                    dbConnectionInfo.getProperty("databasePassword"));
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            new DatabaseConnection();
        }
        return conn;
    }


    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
