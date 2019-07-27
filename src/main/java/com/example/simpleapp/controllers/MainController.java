package com.example.simpleapp.controllers;

import com.example.simpleapp.domain.Message;
import com.example.simpleapp.domain.User;
import com.example.simpleapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model
//            @RequestParam(name = "name", required = false, defaultValue = "default value") String name,
    ) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages;

        if (!StringUtils.isEmpty(filter)) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messageList", "Список сообщений");
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);


        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag,
                             @RequestParam("file") MultipartFile file,
                             Map<String, Object> model) {

        Message message = new Message(text, tag, user);
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setFilename(file.getName());
        }
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

        return "main";
    }
}