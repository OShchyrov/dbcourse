package com.database.course.controller;

import com.database.course.dao.PurchaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseDAO purchaseDAO;

    @GetMapping(value = {"", "/"})
    public String purchase() {
        return "purchase/purchase";
    }

    @PostMapping(value = {"", "/"})
    public String purchase(Map<String, Object> model,
                           @RequestParam String shopName,
                           @RequestParam String date) {

        model.put("data", purchaseDAO.getPurchase(shopName, date));
        return "purchase/purchase";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String date) {
        if (productName != null && date != null) {
            model.put("product", purchaseDAO.findByNameAndDate(productName, date));
        } else {
            model.put("data", purchaseDAO.findAll());
        }
        return "purchase/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String productName,
                       @RequestParam String oldDate,
                       @RequestParam String date,
                       @RequestParam String price) {

        purchaseDAO.update(productName, oldDate, date, price);
        return "redirect:/purchase/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String productName,
                         @RequestParam String date) {
        purchaseDAO.remove(productName, date);
        return "redirect:/purchase/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "purchase/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String productName,
                         @RequestParam String date,
                         @RequestParam String price) {
        purchaseDAO.addPurchase(productName, date, price);
        return "redirect:/purchase/edit/";
    }
}