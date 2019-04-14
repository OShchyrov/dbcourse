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
    public String login(Map<String, Object> model,
                        @RequestParam String login,
                        @RequestParam String password,
                        HttpServletResponse response) {
        boolean isValid = userDAIO.isUserValid(login, password);
        if (isValid) {
            response.addCookie(new Cookie("uuid", login));
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        response.addCookie(new Cookie("uuid", "") {{
            setMaxAge(0);
        }});
        return "redirect:/login/";
    }
}