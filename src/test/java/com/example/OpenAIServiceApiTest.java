//package com.example;
//
//import com.example.model.Message;
//import com.example.service.OpenAIService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.ActiveProfiles;
//
//import javax.imageio.IIOException;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@ActiveProfiles("test") // If you have different profiles
//public class OpenAIServiceApiTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private OpenAIService openAiService;
//
//    private MockMultipartFile mockFile;
//    private MockMultipartFile mockFile2;
//
//   @BeforeEach
//   public void setup() throws IOException {
//       //create a mock csv file for testing
//       File file = new File("test.csv");
//       FileInputStream inputStream = new FileInputStream(file);
//       //constructor for MockMultiPartFile, one is (String name, originalFileName, String contentType, InputStream contentStream)
//       mockFile = new MockMultipartFile(
//               "file",
//               file.getName(),
//               MediaType.TEXT_PLAIN_VALUE,
//               inputStream
//       );
//
//       File file2 = new File("test2.csv");
//       FileInputStream inputStream2 = new FileInputStream(file2);
//       mockFile2 = new MockMultipartFile(
//               "file",
//               file2.getName(),
//               MediaType.TEXT_PLAIN_VALUE,
//               inputStream2
//       );
//    }
//
//    @Test
//    public void testChatApi() {
//       List<Message> result = openAiService.chat(mockFile);
//       List<Message> result2 = openAiService.chat(mockFile2);
//
//       //you can add more assertions based on your expected result
//        assertEquals(5, result.size());
//        assertEquals(4, result2.size());
//
//
//
//    }
//
//    @Test
//    public void containsOneWord(){
//       List<Message> result = openAiService.chat(mockFile);
//
//       for( Message msg : result){
//           String exp = msg.getContent();
//           int strLength = exp.split("//s+").length;
//           assertEquals(1, strLength);
//       }
//
//    }
//}
//
