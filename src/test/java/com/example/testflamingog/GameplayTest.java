package com.example.testflamingog;


import com.example.testflamingog.contrillers.dto.MoveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.example.testflamingog.domain.enums.GameStatus.X_TURN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameplayTest { //TODO draw and failure tests
    private final static String testGameId ="G_ID";
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void start() {
        ResponseEntity<String> response = restTemplate
                .postForEntity("/games/"+testGameId+"/start","", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"moveStatus\":\"X_TURN\"}", response.getBody());
    }

    @Test
    public void playGameXwin() {
        restTemplate.postForEntity("/games/"+testGameId+"/start","", String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity1 = new HttpEntity<>("{\"OX\": \"X_TURN\", \"place\": 1}",headers);
        HttpEntity<String> requestEntity2 = new HttpEntity<>("{\"OX\": \"O_TURN\", \"place\": 0}",headers);
        HttpEntity<String> requestEntity3 = new HttpEntity<>("{\"OX\": \"X_TURN\", \"place\": 4}",headers);
        HttpEntity<String> requestEntity4 = new HttpEntity<>("{\"OX\": \"O_TURN\", \"place\": 2}",headers);
        HttpEntity<String> requestEntity5 = new HttpEntity<>("{\"OX\": \"X_TURN\", \"place\": 7}",headers);
        restTemplate.postForEntity("/games/"+testGameId+"/move",requestEntity1,String.class);
        restTemplate.postForEntity("/games/"+testGameId+"/move",requestEntity2,String.class);
        restTemplate.postForEntity("/games/"+testGameId+"/move",requestEntity3,String.class);
        restTemplate.postForEntity("/games/"+testGameId+"/move",requestEntity4,String.class);
        ResponseEntity<String> response = restTemplate
                .postForEntity("/games/"+testGameId+"/move",requestEntity5,String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"moveStatus\":\"X_WIN\"}", response.getBody());

    }

}
