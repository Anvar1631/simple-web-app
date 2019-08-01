package com.example.simpleapp.controllers;

import com.example.simpleapp.domain.User;
import com.example.simpleapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    private String addUser(User user, Map<String, Object> model) {

        boolean isAdded = userService.addUser(user);
        if (!isAdded) {
            model.put("message", "User with this already exists");
            return "registration";
        }

        return "redirect:/login";
    }
}
