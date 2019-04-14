package com.database.course.controller;

import com.database.course.dao.LikedDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/liked")
public class LikedController {

    @Autowired
    private LikedDAO likedDAO;

    @GetMapping(value = {"", "/"})
    public String liked() {
        return "liked/liked";
    }

    @PostMapping(value = {"", "/"})
    public String liked(Map<String, Object> model,
                         @CookieValue(value = "uuid") String user,
                         @RequestParam String exist) {

        model.put("data", likedDAO.getLiked(user, exist));
        return "liked/liked";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String productName) {
        if (productName != null) {
            model.put("product", likedDAO.findByName(productName));
        } else {
            model.put("data", likedDAO.findAll());
        }
        return "liked/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String productName,
                       @RequestParam String user,
                       @RequestParam String exist) {

        likedDAO.update(productName, user, exist);
        return "redirect:/liked/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String productName) {
        likedDAO.remove(productName);
        return "redirect:/liked/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "liked/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String productName,
                         @RequestParam String user,
                         @RequestParam String exist) {
        likedDAO.addLiked(productName, user, exist);
        return "redirect:/liked/edit/";
    }
}