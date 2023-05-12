package org.iismagilov.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

    public static Connection dbConnection;

    public static Connection getConnection() {
        try {
            Connection dbConnection = null;
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC Driver is work!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Where is your PostgreSQL JDBC Driver?");
            e.printStackTrace();
        }
        try {
            String url = "jdbc:postgresql://localhost:5432/MyDatabase";
            String username = "postgres";
            String password = "Avaya123!";
            dbConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is ready!");
        } catch (SQLException ex) {
            System.err.println("Error: Check the correctness of the url, login or password!");
            ex.printStackTrace();
        }
        return dbConnection;
    }
    /*
    public static Connection getConnection() {
        try{
            if(dbConnection.isClosed()) {
                setConnection();
            } else {
                boolean connectionClosed = dbConnection.isClosed();
                boolean connectionValid = dbConnection.isValid(1);
                if (connectionClosed || !connectionValid) {
                    setConnection();
                }
            }
        } catch (SQLException ex){
            System.err.println("Error: Connection is failed...");
            ex.printStackTrace();
        }
        return dbConnection;
    }
     */
}

