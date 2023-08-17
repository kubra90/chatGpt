package com.example;

import com.example.service.OpenAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientRequestCallback;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientResponseExtractor;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientResponseErrorHandler;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test") // If you have different profiles
public class OpenAIServiceApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OpenAIService openAiService

    @Test
    public void testChatApi() {
        // Define your request data here
        // ...

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/api/chat", // The API endpoint
                HttpMethod.POST, // HTTP method
                null, // Request entity or body
                String.class // Expected response type
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Perform additional assertions on the response
    }
}

