package com.example.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TelephonyInfor implements Serializable {
    String name;
    String phone;
    NetworkOperator networkOperator;
    private String carrier;

    public TelephonyInfor(String name, String phone, NetworkOperator networkOperator) {
        this.name = name;
        this.phone = phone;
        this.networkOperator = networkOperator;
    }

    public TelephonyInfor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public NetworkOperator getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(NetworkOperator networkOperator) {
        this.networkOperator = networkOperator;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " (" + phone + ", " + networkOperator.getDisplayName() + ")";
    }
}