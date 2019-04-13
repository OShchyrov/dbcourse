package com.database.course.controller;

import com.database.course.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping(value = {"", "/"})
    public String products() {
        return "products/products";
    }

    @PostMapping(value = {"", "/"})
    public String products(Map<String, Object> model,
                           @RequestParam String shopname,
                           @RequestParam String category,
                           @RequestParam String price) {

        model.put("data", productDAO.getProducts(shopname, category, price));
        return "products/products";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String category) {
        if (productName != null && category != null) {
            model.put("product", productDAO.findByNameAndCategory(productName, category));
        } else {
            model.put("data", productDAO.findAll());
        }
        return "products/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String oldProductName,
                       @RequestParam String oldCategory,
                       @RequestParam String productName,
                       @RequestParam String category,
                       @RequestParam String price,
                       @RequestParam String shopName) {

        productDAO.update(oldProductName, oldCategory, productName, category, price, shopName);
        return "redirect:/products/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String productName,
                         @RequestParam String category) {
        productDAO.remove(productName, category);
        return "redirect:/products/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "products/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String productName,
                         @RequestParam String category,
                         @RequestParam String price,
                         @RequestParam String shopName) {
        productDAO.addProduct(productName, category, price, shopName);
        return "redirect:/products/edit/";
    }
}