package com.database.course.entity;

public class Purchase {

    private String productName;
    private String date;
    private String price;
    private String shopName;

    public Purchase(String productName, String date, String price, String shopName) {
        this.productName = productName;
        this.date = date;
        this.price = price;
        this.shopName = shopName;
    }

    public String getProductName() {
        return productName;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getShopName() {
        return shopName;
    }
}