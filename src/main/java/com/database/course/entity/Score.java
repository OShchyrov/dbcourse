package com.database.course.entity;

public class Score {

    private String productName;
    private String shopName;
    private String score;

    public Score(String productName, String shopName, String score) {
        this.productName = productName;
        this.shopName = shopName;
        this.score = score;
    }

    public String getProductName() {
        return productName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getScore() {
        return score;
    }
}