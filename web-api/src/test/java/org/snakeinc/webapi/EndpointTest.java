package org.snakeinc.webapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.webapi.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void testPostRequest() {
        Player player =  new Player("test", 60);
        ResponseEntity<Player> response = restTemplate.postForEntity("/api/v1/players/", player, Player.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player.getId(), response.getBody().getId());
    }

    @Test
    void testGetRequest() {
        restTemplate.postForEntity("/api/v1/players/", new Player("test", 60), Player.class);
        ResponseEntity<Player> response = restTemplate.getForEntity("/api/v1/players/0", Player.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().getId());
    }
}
