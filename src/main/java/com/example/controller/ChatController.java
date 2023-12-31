package com.example.controller;

import com.example.model.Message;
import com.example.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatController {

    @Autowired
    OpenAIService service;


    @PostMapping("/chat")
    public List<Message> getChat(@RequestParam("file") MultipartFile file, @RequestParam("promptText") String promptMsg){
        //pass in file
        return service.chat(file, promptMsg);
    }

}
