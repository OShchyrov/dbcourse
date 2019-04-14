package com.database.course.dao;

import com.database.course.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagersDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Manager> managerMapper = (rs, rowNum) -> new Manager(
            rs.getString("manager"),
            rs.getString("shopname"),
            rs.getString("sold_products")
    );

    public List<Manager> getManagers(String shopName, String sold) {
        return template.query("SELECT * FROM shop_manager " +
                "WHERE shopname = ? AND sold_products > ?", managerMapper, shopName, sold);
    }

    public List<Manager> findAll() {
        return template.query("SELECT * FROM shop_manager", managerMapper);
    }

    public void update(String manager, String sold, String shopName) {
        template.update("UPDATE shop_manager " +
                "SET sold_products = ? " +
                "WHERE shopname = ? AND manager = ?", sold, shopName, manager);
    }

    public void remove(String manager) {
        template.update("DELETE FROM shop_manager WHERE manager = ?", manager);
    }

    public Manager findByName(String manager) {
        return template.query("SELECT * FROM shop_manager " +
                        "WHERE manager = ?",
                managerMapper, manager).get(0);
    }

    public void addManager(String manager, String sold, String shopName) {
        template.update("INSERT INTO shop_manager VALUES (?,?,?)", manager, sold, shopName);
    }
}