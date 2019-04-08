package com.database.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping(name = "/")
    public String index() {
        return "main";
    }

    @RequestMapping(name = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public String login(Map<String, Object> model,
                        @RequestParam String login,
                        @RequestParam String password) {

        return "login";
    }
}