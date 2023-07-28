package com.example.controller;

import com.example.model.Message;
import com.example.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    OpenAIService service;

    @GetMapping("/chat")
    public Message getChat(@RequestParam String prompt){
        return service.chat(prompt);
    }

}
