package org.iismagilov.controller;

public class Account {
    private Integer id;
    private Integer idClient;
    private String accountNumber;
    private Double Summa;
    private String accountStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (idClient > 0) {
            this.id = id;
        }
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        if (idClient > 0) {
            this.idClient = idClient;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (!accountNumber.isEmpty()) {
            this.accountNumber = accountNumber;
        }
    }

    public Double getSumma() {
        return Summa;
    }

    public void setSumma(Double summa) {
        Summa = summa;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBIK() {
        return BIK;
    }

    final private String BIK = "501001121";
   public enum Currency{
       RUB,
       EUR,
       USD
   }

}
