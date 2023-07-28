package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class ChatRequestDto {

    private String model;
    private List<Message> messages;
    private int n;

    public ChatRequestDto(String model, String prompt){
        this.model =model;
        messages = new ArrayList<>();
        messages.add(new Message("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
