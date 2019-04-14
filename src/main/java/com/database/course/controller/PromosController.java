package com.database.course.controller;

import com.database.course.dao.PromoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/promos")
public class PromosController {

    @Autowired
    private PromoDAO promoDAO;

    @GetMapping(value = {"", "/"})
    public String promos() {
        return "promos/promos";
    }

    @PostMapping(value = {"", "/"})
    public String promos(Map<String, Object> model,
                         @RequestParam String shopName,
                         @RequestParam String city) {

        model.put("data", promoDAO.getPromos(shopName, city));
        return "promos/promos";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String shopName) {
        if (productName != null && shopName != null) {
            model.put("product", promoDAO.findByNameAndShop(productName, shopName));
        } else {
            model.put("data", promoDAO.findAll());
        }
        return "promos/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String productName,
                       @RequestParam String shopName,
                       @RequestParam String promo) {

        promoDAO.update(productName, shopName, promo);
        return "redirect:/promos/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String productName,
                         @RequestParam String shopName) {
        promoDAO.remove(productName, shopName);
        return "redirect:/promos/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "promos/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String productName,
                         @RequestParam String shopName,
                         @RequestParam String promo) {
        promoDAO.addPromo(productName, shopName, promo);
        return "redirect:/promos/edit/";
    }
}