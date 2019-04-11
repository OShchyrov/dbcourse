package com.database.course.entity;

public class Shop {

    private String shopName;
    private String category;
    private String city;
    private String lat;
    private String lng;
    private String manager;

    public Shop(String shopName, String category, String city, String lat, String lng, String manager) {
        this.shopName = shopName;
        this.category = category;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
        this.manager = manager;
    }

    public String getShopName() {
        return shopName;
    }

    public String getCategory() {
        return category;
    }

    public String getCity() {
        return city;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getManager() {
        return manager;
    }
}