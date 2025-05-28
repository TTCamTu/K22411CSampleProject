package com.example.models;

public class Account {
    private int Account_ID;
    private String Account_name;
    private String Account_password;

    public Account() {
    }

    public Account(int account_ID, String account_name, String account_password) {
        Account_ID = account_ID;
        Account_name = account_name;
        Account_password = account_password;
    }

    public int getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(int account_ID) {
        Account_ID = account_ID;
    }

    public String getAccount_name() {
        return Account_name;
    }

    public void setAccount_name(String account_name) {
        Account_name = account_name;
    }

    public String getAccount_password() {
        return Account_password;
    }

    public void setAccount_password(String account_password) {
        Account_password = account_password;
    }
}
