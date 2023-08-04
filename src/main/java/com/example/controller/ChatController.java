package com.example.controller;

import com.example.model.Message;
import com.example.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin
@RestController
public class ChatController {

    @Autowired
    OpenAIService service;

//    @GetMapping("/chat")
//    public Message getChat(@RequestParam String prompt){
//        return service.chat(prompt);
//    }

    @PostMapping("/chat")
    public List<Message> getChat(@RequestParam("file") MultipartFile file){
        //pass in file
        return service.chat(file);
    }

}
