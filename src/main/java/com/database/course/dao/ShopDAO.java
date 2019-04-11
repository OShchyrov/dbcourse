package com.database.course.dao;

import com.database.course.entity.Shop;
import javafx.scene.paint.Stop;
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

    public List<Shop> findAll() {
        return template.query("SELECT * FROM shops s INNER JOIN shops_categories c " +
                "ON s.shopname = c.shopname ", shopMapper);
    }

    public Shop findByNameAndCity(String shopname, String city) {
        return template.query("SELECT * FROM shops s INNER JOIN shops_categories c " +
                "ON s.shopname = c.shopname " +
                "WHERE c.shopname = ? AND s.city = ?", shopMapper, shopname, city).get(0);
    }

    public void update(String oldShopname, String oldCity, String shopname, String category, String city, String latitude, String longitude, String manager) {
        template.update("UPDATE shops s INNER JOIN shops_categories sc ON s.shopname = sc.shopname " +
                        "INNER JOIN product_shop ps ON s.shopname = ps.shop_name " +
                        "INNER JOIN product_score psc ON psc.shopname = s.shopname " +
                        "INNER JOIN products_promo pp ON pp.shopname = s.shopname " +
                        "INNER JOIN shop_manager sm ON sm.shopname = s.shopname " +
                        "SET s.shopname = ?, sc.shopname = ?, ps.shop_name = ?, psc.shopname = ?, pp.shopname = ?, sm.shopname = ?, " +
                        "sc.category = ?, s.city = ?, s.latitude = ?, s.longitude = ?, s.manager = ?, sm.manager = ? " +
                        "WHERE s.shopname = ? AND s.city = ?",
                shopname, shopname, shopname, shopname, shopname, shopname,
                category, city, latitude, longitude, manager, manager,
                oldShopname, oldCity);
    }

    public void remove(String shopname, String city) {
        template.update("DELETE FROM shops WHERE shopname = ? AND city = ?", shopname, city);
        template.update("DELETE FROM shops_categories WHERE shopname = ?", shopname);
        template.update("DELETE FROM product_shop WHERE shop_name = ?", shopname);
        template.update("DELETE FROM product_score WHERE shopname = ?", shopname);
        template.update("DELETE FROM shop_manager WHERE shopname = ?", shopname);
    }
}