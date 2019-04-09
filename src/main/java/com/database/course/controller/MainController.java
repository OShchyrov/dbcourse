package com.database.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String index() {
        return "main";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(Map<String, Object> model,
                        @RequestParam String login,
                        @RequestParam String password) {

        return "login";
    }
}