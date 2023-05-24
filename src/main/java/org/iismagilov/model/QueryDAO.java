package org.iismagilov.model;

import org.iismagilov.controller.Client;

import java.sql.*;
import java.util.ArrayList;

public class QueryDAO {

    private final static String clientsTable = "CLIENTS";
    private final static String accountsTable = "ACCOUNTS";

    public static void insertClient(String firstName, String surName, String lastName, String phone_number, String inn, String address) {
        try {
        String sql = "INSERT INTO " + clientsTable
                + "(firstName,surName,lastName,phone_number,inn,address) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
        stmt.setString(1, firstName);
        stmt.setString(2, surName);
        stmt.setString(3, lastName);
        stmt.setString(4, phone_number);
        stmt.setString(5, inn);
        stmt.setString(6, address);
        stmt.executeUpdate();
        System.out.println("Query insertClient is complete!");
        } catch (SQLException ex){
            System.err.println("Query insertClient is failed...");
            System.err.println(ex);
        }
    }
    public static boolean deleteClient(Integer id) {
        boolean deleteSuccessful = true;
            try {
                String sql = "DELETE FROM "+ clientsTable +" WHERE id = ?";
                PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Query deleteClient is complete!");
            } catch (SQLException ex){
                deleteSuccessful = false;
                System.err.println("Query deleteClient is failed...");
                System.err.println(ex);
            }
            return deleteSuccessful;
    }

    public static ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();

        try {
            String sql = "SELECT id,firstName,surName,lastName,phone_number,inn,address FROM " + clientsTable + " ORDER BY 3";
            Statement stmt = ConnectionDAO.getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String surName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                String inn = resultSet.getString(6);
                String address = resultSet.getString(7);
                Client client = new Client(id, firstName, surName, lastName, phoneNumber, inn, address);
                clients.add(client);
                }
            for (Client cl:clients) {
                System.out.println("Client: id = " + cl.getId()
                        + ", Full name: " + cl.getSurName() + " " + cl.getFirstName() + " " + cl.getLastName()
                        +", Phone: " + cl.getPhoneNumber()
                        +", INN: "+cl.getInn()
                        +", Address: "+cl.getAddress());
            }

            System.out.println("Query getClients is complete!");
        } catch (SQLException ex){
            System.err.println("Query getClients is failed...");
            System.err.println(ex);
        }
        return clients;
    }

    public static Client selectClient(Integer id) {
        Client client = null;
        try{
            String sql = "SELECT id,firstName,surName,lastName,phone_number,inn,address FROM "
                    + clientsTable + " WHERE id = ?";
            PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                Integer idClient = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String surName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                String inn = resultSet.getString(6);
                String address = resultSet.getString(7);
                client = new Client(idClient, firstName, surName, lastName, phoneNumber, inn, address);
            }
        } catch (SQLException ex){
            System.err.println("Query selectClient is failed...");
            System.err.println(ex);
        }
        return client;
    }

    public static void updateClient(Integer id, String firstName, String surName, String lastName, String phone_number, String inn, String address){
        try {
            String sql = "UPDATE " + clientsTable + " SET ";
            Boolean isFirstAgr = true;
            if (!firstName.isEmpty()) {
                sql = sql + "firstName = '" + firstName + "'";
                isFirstAgr = false;
            }

            if (!surName.isEmpty()) {
                if (!isFirstAgr) {
                    sql = sql + ", surName = '" + surName + "'";
                } else {
                    sql = sql + "surName = '" + surName + "'";
                    isFirstAgr = false;
                }
            }

            if (!lastName.isEmpty()) {
                if (!isFirstAgr) {
                    sql = sql + ", lastName = '" + lastName + "'";
                } else {
                    sql = sql + "lastName = '" + lastName + "'";
                    isFirstAgr = false;
                }
            }

            if (!phone_number.isEmpty()) {
                if (!isFirstAgr) {
                    sql = sql + ", phone_number = '" + phone_number + "'";
                } else {
                    sql = sql + "phone_number = '" + phone_number + "'";
                    isFirstAgr = false;
                }
            }

            if (!inn.isEmpty()) {
                if (!isFirstAgr) {
                    sql = sql + ", inn = '" + inn + "'";
                } else {
                    sql = sql + "inn = '" + inn + "'";
                    isFirstAgr = false;
                }
            }

            if (!address.isEmpty()) {
                if (!isFirstAgr) {
                    sql = sql + ", address = '" + address + "'";
                } else {
                    sql = sql + "address = '" + address + "'";
                }
            }

            sql = sql + " WHERE id = ?";
        PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Query updateClient is complete!");
        } catch (SQLException ex){
            System.err.println("Query updateClient is failed...");
            System.err.println(ex);
        }

    }

}
