package com.example.models;

public class Account {
    private int Account_ID;
    private String Account_Name;
    private String Account_password;
    public Account() {
    }
    public Account(int account_ID, String account_Name, String account_password) {
        Account_ID = account_ID;
        Account_Name = account_Name;
        Account_password = account_password;
    }

    public int getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(int account_ID) {
        Account_ID = account_ID;
    }

    public String getAccount_Name() {
        return Account_Name;
    }

    public void setAccount_Name(String account_Name) {
        Account_Name = account_Name;
    }

    public String getAccount_password() {
        return Account_password;
    }

    public void setAccount_password(String account_password) {
        Account_password = account_password;
    }
}
