package org.iismagilov;

import java.sql.PreparedStatement;

public class QueryDAO {
    public static String insertClient(String firstName, String surName, String lastName, String phone_number, String inn, String address){
        String sql = "INSERT INTO CLIENT"
                + "(firstName,surName,lastName,phone_number,inn,address) VALUES ('"
                        + firstName + "','"
                        + surName + "','"
                        + lastName + "','"
                        + phone_number + "','"
                        + inn + "','"
                        + address + "')";

        return sql;

        //PreparedStatement stmt = currentCon.
        //stmt.executeUpdate();
    }
    /*
    public void updateField(int id, Object o){
        String sql = "UPDATE CLIENT"
                SET o = 1 where id = id

        return sql;
    }
     */
}
