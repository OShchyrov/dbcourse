package com.database.course.controller;

import com.database.course.dao.CouponDAO;
import com.database.course.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/coupons")
public class CouponsController {

    @Autowired
    private CouponDAO couponDAO;

    @GetMapping(value = {"", "/"})
    public String coupons() {
        return "coupons/coupons";
    }

    @PostMapping(value = {"", "/"})
    public String coupons(Map<String, Object> model,
                          @RequestParam String productName,
                          @RequestParam String amount) {

        model.put("data", couponDAO.getCoupons(productName, amount));
        return "coupons/coupons";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String productName) {
        if (productName != null) {
            model.put("product", couponDAO.findByName(productName));
        } else {
            model.put("data", couponDAO.findAll());
        }
        return "coupons/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String productName,
                       @RequestParam String amount,
                       @RequestParam String type) {

        couponDAO.update(productName, amount, type);
        return "redirect:/coupons/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String productName) {
        couponDAO.remove(productName);
        return "redirect:/coupons/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "coupons/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String productName,
                         @RequestParam String amount,
                         @RequestParam String type) {
        couponDAO.addCoupon(productName, amount, type);
        return "redirect:/coupons/edit/";
    }
}