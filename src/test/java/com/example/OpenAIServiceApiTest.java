package com.example;

import com.example.model.Message;
import com.example.service.OpenAIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientRequestCallback;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientResponseExtractor;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientResponseErrorHandler;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import javax.imageio.IIOException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test") // If you have different profiles
public class OpenAIServiceApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OpenAIService openAiService;

    private MockMultipartFile mockFile;

   @BeforeEach
   public void setup() throws IOException {
       //create a mock csv file for testing
       ClassPathResource resource =new ClassPathResource("test.csv");
       mockFile = new MockMultipartFile(
               "file",
               resource.getInputStream()
       );
    }

    @Test
    public void testChatApi() {
       List<Message> result = openAiService.chat(mockFile);
    }
}

