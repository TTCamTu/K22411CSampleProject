package com.example.models;

import java.util.Arrays;
import java.util.List;

public class ListAccount {
    public static List<Account> getAccounts() {
        return Arrays.asList(
                new Account(1, "user1", "1234"),
                new Account(2, "user2", "5678"),
                new Account(3, "user3", "9123")
        );
    }
}
