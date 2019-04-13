package com.database.course.controller;

import com.database.course.dao.ScoresDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/scores")
public class ScoresController {

    @Autowired
    private ScoresDAO scoresDAO;

    @GetMapping(value = {"", "/"})
    public String scores() {
        return "scores/scores";
    }

    @PostMapping(value = {"", "/"})
    public String scores(Map<String, Object> model,
                         @RequestParam String shopName,
                         @RequestParam String productName) {

        model.put("data", scoresDAO.getScores(shopName, productName));
        return "scores/scores";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String shopName) {
        if (productName != null && shopName != null) {
            model.put("product", scoresDAO.findByNameAndShop(productName, shopName));
        } else {
            model.put("data", scoresDAO.findAll());
        }
        return "scores/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String productName,
                       @RequestParam String shopName,
                       @RequestParam String score) {

        scoresDAO.update(productName, shopName, score);
        return "redirect:/scores/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String productName,
                         @RequestParam String shopName) {
        scoresDAO.remove(productName, shopName);
        return "redirect:/scores/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "scores/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String productName,
                         @RequestParam String shopName,
                         @RequestParam String score) {
        scoresDAO.addScore(productName, shopName, score);
        return "redirect:/scores/edit/";
    }
}