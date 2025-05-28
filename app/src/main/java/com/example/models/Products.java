package com.example.models;

public class Products {
    private int id;
    private String productCode;
    private String productName;
    private double unitPrice;
    private String imageLink;

    public Products() {
    }

    public Products(int id, String productCode, String productName, double unitPrice, String imageLink) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
