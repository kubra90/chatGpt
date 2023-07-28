package com.example.service;

import com.example.model.ChatRequestDto;
import com.example.model.ChatResponseDto;
import com.example.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class OpenAIService {

    //I need to use API_KEY and URL

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${API_URL}")
    private String url;
    @Value("${API_MODEL}")
    private String model;

   private RestTemplate template = new RestTemplate();

   public Message chat(String prompt){
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(apiKey);

       ChatRequestDto request = new ChatRequestDto(model, prompt);
       HttpEntity<ChatRequestDto> entity = new HttpEntity<>(request, headers);

       ResponseEntity<ChatResponseDto> response = template.postForEntity(url, entity, ChatResponseDto.class);
       return response.getBody().getChoices().get(0).getMessage();
   }

}
