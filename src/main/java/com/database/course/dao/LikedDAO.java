package com.database.course.dao;

import com.database.course.entity.Liked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikedDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Liked> likedMapper = (rs, rowNum) -> new Liked(
            rs.getString("product_name"),
            rs.getString("user"),
            rs.getString("exist")
    );

    public List<Liked> getLiked(String user, String exist) {
        return template.query("SELECT * FROM product_exists " +
                "WHERE exist = ? AND user = ?", likedMapper, exist, user);
    }

    public List<Liked> findAll() {
        return template.query("SELECT * FROM product_exists", likedMapper);
    }

    public void update(String productName, String user, String exist) {
        template.update("UPDATE product_exists " +
                "SET exist = ?, user = ? " +
                "WHERE product_name = ?", exist, user, productName);
    }

    public void remove(String productName) {
        template.update("DELETE FROM product_exists WHERE product_name = ?", productName);
    }

    public Liked findByName(String productName) {
        return template.query("SELECT * FROM product_exists WHERE product_name = ?",
                likedMapper, productName).get(0);
    }

    public void addLiked(String productName, String user, String exist) {
        template.update("INSERT INTO product_exists VALUES (?,?,?)", productName, user, exist);
    }
}