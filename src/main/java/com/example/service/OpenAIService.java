package com.example.service;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Service
public class OpenAIService {

    //I need to use API_KEY and URL

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${API_URL}")
    private String url;
    @Value("${API_MODEL}")
    private String model;

   private RestTemplate template = new RestTemplate();
   FileReader file= new FileReader("prompt.csv");
    List<Message> output = file.readingFile();

    OutputFile doc=new OutputFile();



    public List<Message> chat(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        List<Message> result =new ArrayList<>();
        for(int i=0; i< output.size(); i++) {
            ChatRequestDto request = new ChatRequestDto(model, "Please label the following text with two words. The text is that " + (output.get(i)));
            HttpEntity<ChatRequestDto> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ChatResponseDto> response = template.postForEntity(url, entity, ChatResponseDto.class);
            result.add(response.getBody().getChoices().get(0).getMessage());

        }
        doc.printFile(result);
        return result;
    }
}
