package com.example.simpleapp.controllers;

import com.example.simpleapp.domain.Message;
import com.example.simpleapp.domain.User;
import com.example.simpleapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model
//            @RequestParam(name = "name", required = false, defaultValue = "default value") String name,
    ) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model map) {
        Iterable<Message> messages;

        if (!StringUtils.isEmpty(filter)) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        map.addAttribute("messageList", "Список сообщений");
        map.addAttribute("messages", messages);
        map.addAttribute("filter", filter);


        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag,
                             Map<String, Object> model) {

        Message message = new Message(text, tag, user);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        model.put("messageList", "Список сообщений");
        model.put("authorName", user.getAuthorities());

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (!StringUtils.isEmpty(filter)) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.put("messageList", "Результат поиска");
        model.put("messages", messages);

        messageRepository.findByTag(filter);
        return "main";
    }
}