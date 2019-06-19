package Connectivity;

import java.sql.*;


public class ConnectionClass {
    private Connection connection;

    public Connection getConnection(){

        String dbName = "HondaShowRoom";
        String username ="root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
