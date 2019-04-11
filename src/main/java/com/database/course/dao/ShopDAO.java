package com.database.course.dao;

import com.database.course.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Shop> shopMapper = (rs, rowNum) -> new Shop(rs.getString("shopname"),
            rs.getString("category"),
            rs.getString("city"),
            rs.getString("latitude"),
            rs.getString("longitude"),
            rs.getString("manager"));

    public List<Shop> getShops(String category, String city) {
        return template.query("SELECT * FROM shops s INNER JOIN shops_categories c " +
                "ON s.shopname = c.shopname " +
                "WHERE c.category = ? AND s.city = ?", shopMapper, category, city);
    }

}