package com.database.course.controller;

import com.database.course.dao.ShopDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/shops")
public class ShopsController {

    @Autowired
    private ShopDAO shopDAO;

    @GetMapping(value = {"", "/"})
    public String shops() {
        return "shops";
    }

    @PostMapping(value = {"", "/"})
    public String shops(Map<String, Object> model,
                        @RequestParam String category,
                        @RequestParam String city,
                        @RequestParam String offers) {

        model.put("data", shopDAO.getShops(category, city));
        return "shops";
    }

}