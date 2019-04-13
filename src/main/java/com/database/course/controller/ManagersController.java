package com.database.course.controller;

import com.database.course.dao.ManagersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/managers")
public class ManagersController {

    @Autowired
    private ManagersDAO managersDAO;

    @GetMapping(value = {"", "/"})
    public String managers() {
        return "managers/managers";
    }

    @PostMapping(value = {"", "/"})
    public String managers(Map<String, Object> model,
                           @RequestParam String shopName,
                           @RequestParam String sold) {

        model.put("data", managersDAO.getManagers(shopName, sold));
        return "managers/managers";
    }

    @GetMapping(value = "/edit")
    public String edit(Map<String, Object> model,
                       @RequestParam(required = false) String manager) {
        if (manager != null) {
            model.put("manager", managersDAO.findByName(manager));
        } else {
            model.put("data", managersDAO.findAll());
        }
        return "managers/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam String manager,
                       @RequestParam String sold,
                       @RequestParam String shopName) {

        managersDAO.update(manager, sold, shopName);
        return "redirect:/managers/edit/";
    }

    @GetMapping(value = "/remove")
    public String remove(@RequestParam String manager) {
        managersDAO.remove(manager);
        return "redirect:/managers/edit/";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        return "managers/insert";
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestParam String manager,
                         @RequestParam String sold,
                         @RequestParam String shopName) {
        managersDAO.addManager(manager, sold, shopName);
        return "redirect:/managers/edit/";
    }
}