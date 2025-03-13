package com.example.testflamingog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameStateTest {
    private final static String testGameId ="G_ID";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void playGameXwinState() {
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
        restTemplate.postForEntity("/games/"+testGameId+"/move",requestEntity5,String.class);
        ResponseEntity<String> response = restTemplate.getForEntity("/games/"+testGameId,String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"data\":{\"board\":[2,1,2" +
                                                  ",0,1,0" +
                                                  ",0,1,0],\"" +
                "moves\":[1,0,4,2,7,0,0,0,0],\"gameStatus\":\"X_WIN\"}}", response.getBody());

    }
}
