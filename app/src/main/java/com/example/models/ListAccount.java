package com.example.models;

import java.util.Arrays;
import java.util.List;

public class ListAccount {
    public static List<Account> getAccounts() {
        return Arrays.asList(
                new Account(1, "user1", "1234"),
                new Account(2, "user2", "5678"),
                new Account(3, "user3", "9012"),
                new Account(4, "user4", "3456"),
                new Account(5, "user5", "7890"),
                new Account(6, "user6", "7315"),
                new Account(7, "user7", "3156"),
                new Account(8, "user8", "2548"),
                new Account(9, "user9", "3456"),
                new Account(10, "user10", "0123")
        );
    }
}
