package com.database.course.dao;

import com.database.course.entity.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Coupon> couponMapper = (rs, rowNum) -> new Coupon(
            rs.getString("product_name"),
            rs.getString("discount_amount"),
            rs.getString("discount_type")
    );

    public List<Coupon> getCoupons(String productName, String amount) {
        return template.query("SELECT * FROM product_discount pd " +
                "INNER JOIN product_discount_type pdt " +
                "ON pd.product_name = pdt.product_name " +
                "WHERE pd.product_name = ? AND pd.discount_amount > ?", couponMapper, productName, amount);
    }

    public List<Coupon> findAll() {
        return template.query("SELECT * FROM product_discount pd " +
                "INNER JOIN product_discount_type pdt " +
                "ON pd.product_name = pdt.product_name", couponMapper);
    }

    public void update(String productName, String amount, String type) {
        template.update("UPDATE product_discount pd " +
                "INNER JOIN product_discount_type pdt ON pd.product_name = pdt.product_name " +
                "SET pd.discount_amount = ?, pdt.discount_type = ? " +
                "WHERE pd.product_name = ?", amount, type, productName);
    }

    public void remove(String productName) {
        template.update("DELETE FROM product_discount_type WHERE product_name = ?", productName);
        template.update("DELETE FROM product_discount WHERE product_name = ?", productName);
    }

    public Coupon findByName(String productName) {
        return template.query("SELECT * FROM product_discount pd " +
                        "INNER JOIN product_discount_type pdt " +
                        "ON pd.product_name = pdt.product_name " +
                        "WHERE pd.product_name = ?",
                couponMapper, productName).get(0);
    }

    public void addCoupon(String productName, String amount, String type) {
        template.update("INSERT INTO product_discount VALUES (?,?)", productName, amount);
        template.update("INSERT INTO product_discount_type VALUES (?,?)", productName, type);
    }
}