package com.epamlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    private static final String ERROR = "error";
    private static final String ERROR_LOGIN_PASSWORD = "You entered an incorrect login and/or password";

    @GetMapping("/login")
    public String login(@RequestParam(name = ERROR, required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute(ERROR, ERROR_LOGIN_PASSWORD);
        }
        return "login";
    }
}
