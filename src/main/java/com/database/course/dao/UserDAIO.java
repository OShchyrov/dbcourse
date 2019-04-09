package com.database.course.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAIO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<String> userMapper = (rs, rowNum) -> rs.getString("username");

    public boolean isUserValid(String username, String password) {
        return template.query("SELECT * FROM users WHERE username = ? AND userpassword = ?", userMapper, username, password).size() == 1;
    }

}