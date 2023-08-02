package com.example.service;

import com.example.model.*;
import com.example.model.FileReader;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
//    FileReader file = new FileReader("prompt.csv");
//    List<Message> output = file.readingFile();

    OutputFile doc = new OutputFile();


    public List<Message> chat(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        List<Message> result = new ArrayList<>();
        //new add
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                //read csv file content
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String line;
                StringBuilder csvContent = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    ChatRequestDto request = new ChatRequestDto(model, "Please label the following text with two words. The text is that " + line);
                    HttpEntity<ChatRequestDto> entity = new HttpEntity<>(request, headers);

                    ResponseEntity<ChatResponseDto> response = template.postForEntity(url, entity, ChatResponseDto.class);
                    result.add(response.getBody().getChoices().get(0).getMessage());
                }

                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //till here
//            for(int i=0; i< output.size(); i++) {
//            ChatRequestDto request = new ChatRequestDto(model, "Please label the following text with two words. The text is that " + (output.get(i)));
//            HttpEntity<ChatRequestDto> entity = new HttpEntity<>(request, headers);
//
//            ResponseEntity<ChatResponseDto> response = template.postForEntity(url, entity, ChatResponseDto.class);
//            result.add(response.getBody().getChoices().get(0).getMessage());
//
//        }
            doc.printFile(result);

        }
        return result;
    }
}
