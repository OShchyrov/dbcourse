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
        return "shops/shops";
    }

    @PostMapping(value = {"", "/"})
    public String shops(Map<String, Object> model,
                        @RequestParam String category,
                        @RequestParam String city,
                        @RequestParam String offers) {

        model.put("data", shopDAO.getShops(category, city));
        return "shops/shops";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String shopname,
                       @RequestParam(required = false) String city) {
        if (shopname != null && city != null) {
            model.put("shop", shopDAO.findByNameAndCity(shopname, city));
        } else {
            model.put("data", shopDAO.findAll());
        }
        return "shops/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String oldShopname,
                       @RequestParam String oldCity,
                       @RequestParam String shopname,
                       @RequestParam String category,
                       @RequestParam String city,
                       @RequestParam String latitude,
                       @RequestParam String longitude,
                       @RequestParam String manager) {

        shopDAO.update(oldShopname, oldCity, shopname, category, city, latitude, longitude, manager);
        return "redirect:/shops/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String shopname,
                         @RequestParam String city) {
        shopDAO.remove(shopname, city);
        return "redirect:/shops/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "shops/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String shopname,
                         @RequestParam String category,
                         @RequestParam String city,
                         @RequestParam String latitude,
                         @RequestParam String longitude,
                         @RequestParam String manager) {
        shopDAO.addShop(shopname, category, city, latitude, longitude, manager);
        return "redirect:/shops/";
    }
}