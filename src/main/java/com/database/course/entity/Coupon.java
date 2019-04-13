package com.database.course.entity;

public class Coupon {

    private String productName;
    private String amount;
    private String type;

    public Coupon(String productName, String amount, String type) {
        this.productName = productName;
        this.amount = amount;
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}