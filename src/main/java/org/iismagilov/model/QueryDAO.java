package org.iismagilov.model;

import org.iismagilov.controller.Client;

import java.sql.*;
import java.util.TreeSet;

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
    public static void deleteClient(Integer id) {
            try {
                String sql = "DELETE FROM "+ clientsTable +" WHERE id = ?";
                PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Query deleteClient is complete!");
            } catch (SQLException ex){
                System.err.println("Query deleteClient is failed...");
                System.err.println(ex);
            }
    }

    public static TreeSet<Client> getClients() {
        TreeSet<Client> clients = new TreeSet<Client>();

        try {
            String sql = "SELECT * FROM " + clientsTable;
            //PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
            //stmt.executeUpdate();
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
                Client client = new Client(id, firstName, surName, lastName,phoneNumber,inn,address);
                clients.add(client);
            }
            System.out.println("Query getClients is complete!");
        } catch (SQLException ex){
            System.err.println("Query getClients is failed...");
            System.err.println(ex);
        }
        return clients;
    }

    public static Client selectIdClient(Integer id) {
        Client client = null;
        try{
            String sql = "SELECT * FROM " + clientsTable + " WHERE id=?";
            PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                Integer idLocal = resultSet.getInt(1);
                client = new Client(idLocal);
            }
        } catch (SQLException ex){
            System.err.println("Query selectIdClient is failed...");
            System.err.println(ex);
        }
        return client;
    }

    public static void updateClient(Integer id, String firstName, String surName, String lastName, String phone_number, String inn, String address){
        try {
            String sql = null;
            if (!firstName.isEmpty()) {
                sql = "UPDATE "+ clientsTable +" SET firstName = " + firstName + "where id = " + id;
            }
            if (!surName.isEmpty()) {
                sql = "UPDATE "+ clientsTable +" SET surName = " + surName + "where id = " + id;
            }
            if (!lastName.isEmpty()) {
                sql = "UPDATE "+ clientsTable +" SET lastName = " + lastName + "where id = " + id;
            }
            if (!phone_number.isEmpty()) {
                sql = "UPDATE "+ clientsTable +" SET phone_number = " + phone_number + "where id = " + id;
            }
            if (!inn.isEmpty()) {
                sql = "UPDATE "+ clientsTable +" SET inn = " + inn + "where id = " + id;
            }
            if (!address.isEmpty()) {
                sql = "UPDATE "+ clientsTable +" SET address = " + address + "where id = " + id;
            }
        if (!sql.isEmpty()){
        PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
        stmt.executeUpdate();
        System.out.println("Query updateClient is complete!");
        }
        } catch (SQLException ex){
            System.err.println("Query updateClient is failed...");
            System.err.println(ex);
        }

    }

}
