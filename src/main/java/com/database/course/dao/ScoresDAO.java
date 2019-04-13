package com.database.course.dao;

import com.database.course.entity.Purchase;
import com.database.course.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoresDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Score> purchaseMapper = (rs, rowNum) -> new Score(
            rs.getString("product_name"),
            rs.getString("shopname"),
            rs.getString("score")
    );

    public List<Score> getScores(String shopName, String productName) {
        return template.query("SELECT * FROM product_score " +
                "WHERE product_name = ? AND shopname = ?", purchaseMapper, productName, shopName);
    }

    public List<Score> findAll() {
        return template.query("SELECT * FROM product_score", purchaseMapper);
    }

    public void update(String productName, String shopName, String score) {
        template.update("UPDATE product_score " +
                "SET score = ?" +
                "WHERE product_name = ? AND shopname = ?", score, productName, shopName);
    }

    public void remove(String productName, String shopName) {
        template.update("DELETE FROM product_score WHERE product_name = ? AND shopname = ?",
                productName, shopName);
    }

    public Score findByNameAndShop(String productName, String shopName) {
        return template.query("SELECT * FROM product_score " +
                        "WHERE product_name = ? AND shopname = ?",
                purchaseMapper, productName, shopName).get(0);
    }

    public void addScore(String productName, String shopName, String score) {
        template.update("INSERT INTO product_score VALUES (?,?,?)", productName, shopName, score);
    }
}