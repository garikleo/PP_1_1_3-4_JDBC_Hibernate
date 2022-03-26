package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3306/pred_1";
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";

    private static final String dbUsername = "root";
    private static final String dbPassword = "root";

    private static Connection connection = null;

    public  static Connection getConnection (){
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
