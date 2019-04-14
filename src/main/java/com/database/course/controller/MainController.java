package com.database.course.controller;

import com.database.course.dao.ShopDAO;
import com.database.course.dao.UserDAIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserDAIO userDAIO;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/shops";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        HttpServletResponse response) {
        boolean isValid = userDAIO.isUserValid(login, password);
        if (isValid) {
            Cookie cookie = new Cookie("uuid", login);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("uuid", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login/";
    }
}