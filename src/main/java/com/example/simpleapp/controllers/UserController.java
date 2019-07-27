package com.example.simpleapp.controllers;

import com.example.simpleapp.domain.Role;
import com.example.simpleapp.domain.User;
import com.example.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    private String userList(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "userList";
    }

    @GetMapping("{user}")
    private String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    private String saveUser(@RequestParam String username,
                            @RequestParam Map<String, String> form,
                            @RequestParam("userId") User user) {
        user.setUsername(username);
        userRepository.save(user);

        Set<String> roles = Arrays
                .stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        return "redirect:/user";
    }
}
