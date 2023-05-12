package org.iismagilov.controller;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class Client {

    private Integer id;
    private String firstName;
    private String surName;
    private String lastName;
    private String phoneNumber;
    private String inn;
    private String address;

    public Client(Integer id, String firstName, String surName, String lastName, String phoneNumber, String inn, String address) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.address = address;
    }

    public Client() {
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

    TextField nameField = new TextField();

    Binder<Client> binder = new Binder<>();

    // Bind nameField to the Person.name property
    // by specifying its getter and setter
    //binder.bind(firstName, Client::getFirstName, Client::setFirstName);

    // Bind an actual concrete Person instance.
    // After this, whenever the user changes the value
    // of nameField, p.setName is automatically called.
        //Client client = new Client();
        //binder.setBean(client);


}
