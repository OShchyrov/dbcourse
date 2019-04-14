package com.database.course.dao;

import com.database.course.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Purchase> purchaseMapper = (rs, rowNum) -> new Purchase(
            rs.getString("product_name"),
            rs.getString("purchase_date"),
            rs.getString("purchase_price"),
            rs.getString("shop_name")
    );

    public List<Purchase> getPurchase(String shopName, String date) {
        return template.query("SELECT * FROM purchase_history p " +
                "INNER JOIN product_shop ps " +
                "ON p.product_name = ps.product_name " +
                "WHERE ps.shop_name = ? AND p.purchase_date = ?", purchaseMapper, shopName, date);
    }

    public List<Purchase> findAll() {
        return template.query("SELECT * FROM purchase_history p " +
                "INNER JOIN product_shop ps " +
                "ON p.product_name = ps.product_name", purchaseMapper);
    }

    public void update(String productName, String oldDate, String date, String price) {
        template.update("UPDATE purchase_history p " +
                "SET p.purchase_date = ?, p.purchase_price= ? " +
                "WHERE p.product_name = ? AND p.purchase_date = ?", date, price, productName, oldDate);
    }

    public void remove(String productName, String date) {
        template.update("DELETE FROM purchase_history WHERE product_name = ? AND purchase_date = ?", productName, date);
    }

    public Purchase findByNameAndDate(String productName, String date) {
        return template.query("SELECT * FROM purchase_history p " +
                        "INNER JOIN product_shop ps " +
                        "ON p.product_name = ps.product_name " +
                        "WHERE p.product_name = ? AND p.purchase_date = ?",
                purchaseMapper, productName, date).get(0);
    }

    public void addPurchase(String productName, String date, String price) {
        template.update("INSERT INTO purchase_history VALUES (?,?,?)", productName, date, price);
    }
}