package com.example.models;

public enum NetworkOperator {
    VIETTEL("Viettel"),
    MOBIFONE("MobiFone"),
    OTHER("Other");

    private final String displayName;

    NetworkOperator(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
