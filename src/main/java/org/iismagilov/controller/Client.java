package org.iismagilov.controller;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.HashSet;
import java.util.Set;

public class Client {

    HashSet<Client> clients;
    private Integer id;
    private String firstName;
    private String surName;
    private String lastName;
    private String phoneNumber;
    private String inn;
    private String address;

    public Client() {
    }
    public Client(Integer id) {
        this.id = id;
    }
    public Client(Integer id, String firstName, String surName, String lastName, String phoneNumber, String inn, String address) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client: id = " + getId()
                + " Full name: "+ getSurName() + getFirstName() + getLastName();
    }
}
