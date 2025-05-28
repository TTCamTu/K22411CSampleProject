package com.example.connectors;

import com.example.models.Account;
import com.example.models.ListAccount;

public class AccountConnector {
    public Account login(String username, String password) {
        ListAccount listAccount = new ListAccount();
        for (Account acc : ListAccount.getAccounts()) {
            if (acc.getAccount_name().equalsIgnoreCase(username) && acc.getAccount_password().equalsIgnoreCase(password)) {
                return acc;
            }
        }
        return null;
    }
}
