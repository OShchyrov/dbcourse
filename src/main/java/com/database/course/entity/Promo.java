package com.database.course.entity;

public class Promo {

    private String productName;
    private String shopName;
    private String promo;

    public Promo(String productName, String shopName, String promo) {
        this.productName = productName;
        this.shopName = shopName;
        this.promo = promo;
    }

    public String getProductName() {
        return productName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getPromo() {
        return promo;
    }
}