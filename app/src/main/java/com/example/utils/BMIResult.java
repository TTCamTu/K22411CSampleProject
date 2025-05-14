package com.example.utils;

public class BMIResult {
    private double BMI;

    private String description;

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDescription() {
        return description;
    }

    public BMIResult() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BMIResult(double BMI, String description) {
        this.BMI = BMI;
        this.description = description;
    }
}
