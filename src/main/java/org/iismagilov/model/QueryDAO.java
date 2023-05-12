package org.iismagilov.model;

import java.sql.*;

public class QueryDAO {

    public static void insertClient(String firstName, String surName, String lastName, String phone_number, String inn, String address) {
        try {
        String sql = "INSERT INTO CLIENT"
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
                String sql = "DELETE FROM CLIENT WHERE id = " + id;
                PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
                stmt.executeUpdate();
                System.out.println("Query deleteClient is complete!");
            } catch (SQLException ex){
                System.err.println("Query deleteClient is failed...");
                System.err.println(ex);
            }
    }

    public static void selectAllClient() {
        try {
            String sql = "SELECT id,surName,firstName,LastName FROM CLIENT";
            PreparedStatement stmt = ConnectionDAO.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Query selectAllClient is complete!");
        } catch (SQLException ex){
            System.err.println("Query deleteClient is failed...");
            System.err.println(ex);
        }
    }
    public static void updateClient(Integer id, String firstName, String surName, String lastName, String phone_number, String inn, String address){
        try {
            String sql = null;
            if (!firstName.isEmpty()) {
                sql = "UPDATE CLIENT SET firstName = " + firstName + "where id = " + id;
            }
            if (!surName.isEmpty()) {
                sql = "UPDATE CLIENT SET surName = " + surName + "where id = " + id;
            }
            if (!lastName.isEmpty()) {
                sql = "UPDATE CLIENT SET lastName = " + lastName + "where id = " + id;
            }
            if (!phone_number.isEmpty()) {
                sql = "UPDATE CLIENT SET phone_number = " + phone_number + "where id = " + id;
            }
            if (!inn.isEmpty()) {
                sql = "UPDATE CLIENT SET inn = " + inn + "where id = " + id;
            }
            if (!address.isEmpty()) {
                sql = "UPDATE CLIENT SET address = " + address + "where id = " + id;
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
