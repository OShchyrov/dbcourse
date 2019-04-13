package com.database.course.entity;

public class Product {

    private String productName;
    private String category;
    private String price;
    private String shopName;

    public Product(String productName, String category, String price, String shopName) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.shopName = shopName;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getShopName() {
        return shopName;
    }
}