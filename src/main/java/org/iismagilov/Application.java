package org.iismagilov;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
    public static void main(String[] args) {
        System.out.println("Begin program!");
        try {
            Connection connection = ConnectionDAO.getConnection();
            //Client client1 = new Client(1);
            //client1.getFullName();
            //System.out.println(client1.toString());
            //Client client = new QueryDAO.createClient("Alsu","Ismagilova","Rafailevna","+79172812826","165112094336","Yamasheva 9");

            //statement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from client");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String phone_number = resultSet.getString(5);
                String inn = resultSet.getString(6);
                String adress = resultSet.getString(7);
                System.out.printf("%d: %s %s %s (phone: %s,inn: %s, address: %s) \n"
                        , id, surname, firstName, lastName, phone_number, inn, adress);
                connection.close();
            }
            System.out.println("End program!");
        }catch (SQLException e) {
            System.err.println("Where is your PostgreSQL JDBC Driver? ");
            e.printStackTrace();
        }
    }
}
