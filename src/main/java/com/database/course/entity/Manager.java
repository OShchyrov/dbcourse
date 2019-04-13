package com.database.course.entity;

public class Manager {

    private String name;
    private String shopName;
    private String sold;

    public Manager(String name, String shopName, String sold) {
        this.name = name;
        this.shopName = shopName;
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public String getShopName() {
        return shopName;
    }

    public String getSold() {
        return sold;
    }
}