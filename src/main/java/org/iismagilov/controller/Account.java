package org.iismagilov.controller;

import java.math.BigDecimal;

public class Account {
    private Integer id;
    private Integer idClient;
    private String accountNumber;
    private BigDecimal summa;
    private String currency;
    private String accountStatus;
    private String bik;

public Account(Integer idAccount, Integer idClient, String accountNumber, BigDecimal summa, String currency, String bik, String status){
    this.id = idAccount;
    this.idClient = idClient;
    this.accountNumber = accountNumber;
    this.summa = summa;
    this.currency = currency;
    this.bik = bik;
    this.accountStatus = status;
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public Integer getIdClient() {return idClient;}

    public String getAccountNumber() {return accountNumber;}

    public BigDecimal getSumma() {return summa;}

    public String getAccountStatus() {
        return accountStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBik() {
        return bik;
    }

   public enum Currency {
       RUB,
       EUR,
       USD;
   }

    @Override
    public String toString() {
        return "Account: id = " + getId()
                + ", Client id: " + getIdClient()
                + ", AccountNumber: " + getAccountNumber()
                + ", Summa: " + getSumma()
                +", Currency: " + getCurrency()
                + ", BIK: "+ getBik()
                + ", Status: " + getAccountStatus();
    }
}
