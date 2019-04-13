package com.database.course.dao;

import com.database.course.entity.Coupon;
import com.database.course.entity.Promo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromoDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Promo> couponMapper = (rs, rowNum) -> new Promo(
            rs.getString("product_name"),
            rs.getString("shopname"),
            rs.getString("promo")
    );

    public List<Promo> getPromos(String shopname, String city) {
        return template.query("SELECT * FROM products_promo pp " +
                "INNER JOIN shops s " +
                "ON pp.shopname = s.shopname " +
                "WHERE pp.shopname = ? AND s.city = ? AND pp.promo = ?", couponMapper, shopname, city, "так");
    }

    public List<Promo> findAll() {
        return template.query("SELECT * FROM products_promo pd", couponMapper);
    }

    public void update(String productName, String shopName, String promo) {
        template.update("UPDATE products_promo " +
                "SET promo = ? " +
                "WHERE product_name = ? AND shopname = ?", promo, productName, shopName);
    }

    public void remove(String productName, String shopName) {
        template.update("DELETE FROM products_promo WHERE product_name = ? AND shopname = ?", productName, shopName);
    }

    public Promo findByNameAndShop(String productName, String shopName) {
        return template.query("SELECT * FROM products_promo pp " +
                        "WHERE pp.product_name = ? AND pp.shopname = ?",
                couponMapper, productName, shopName).get(0);
    }

    public void addPromo(String productName, String shopName, String promo) {
        template.update("INSERT INTO products_promo VALUES (?,?,?)", productName, shopName, promo);
    }
}