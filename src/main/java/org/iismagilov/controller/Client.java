package org.iismagilov.controller;

public class Client {

    //public ArrayList<Client> clients;
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

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInn() {return inn;}
    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getAddress() {return address;}
    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Client: id = " + getId()
                + ", Full name: "+ getSurName() + " " + getFirstName() + " " + getLastName()
                + ", Phone: "+getPhoneNumber()
                +", INN: "+getInn()
                +", Address: "+getAddress();
    }
}
