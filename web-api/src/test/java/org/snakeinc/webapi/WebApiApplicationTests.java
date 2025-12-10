package org.snakeinc.webapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snakeinc.webapi.entity.Player;
import org.snakeinc.webapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest @ExtendWith(MockitoExtension.class)
class WebApiApplicationTests {
    @InjectMocks
    private PlayerService playerService;
    @Test
    void testCreatePlayer() {
        Player player = new Player("test", 14);
        Assertions.assertEquals("test", player.getName());
        Assertions.assertEquals(14, player.getAge());
    }

    @Test
    void testPlayerService() {
        Player player = new Player("test", 14);
        playerService.createPlayer(player);
        Player player2 = playerService.getPlayer(player.getId());
        Assertions.assertNotNull(player2);
        Assertions.assertEquals("test", player2.getName());
        Assertions.assertEquals(14, player2.getAge());
    }

}

