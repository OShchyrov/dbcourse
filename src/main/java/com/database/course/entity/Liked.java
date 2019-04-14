package com.database.course.entity;

public class Liked {

    private String productName;
    private String user;
    private String exist;

    public Liked(String productName, String user, String exist) {
        this.productName = productName;
        this.user = user;
        this.exist = exist;
    }

    public String getProductName() {
        return productName;
    }

    public String getUser() {
        return user;
    }

    public String getExist() {
        return exist;
    }
}