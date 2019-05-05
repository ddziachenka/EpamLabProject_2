package com.epamlab.controller;

import com.epamlab.model.User;
import com.epamlab.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CabinetController {
    private static final String USER = "user";
    private final UserService<User> userUserServiceImpl;

    public CabinetController(UserService<User> userUserServiceImpl) {
        this.userUserServiceImpl = userUserServiceImpl;
    }

    @RequestMapping("/cabinet")
    public String getCabinet(Model model) {
        User user = userUserServiceImpl.getCurrentUser();
        model.addAttribute(USER, user);
        return "cabinet";
    }
}
