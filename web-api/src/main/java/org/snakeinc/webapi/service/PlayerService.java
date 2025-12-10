package org.snakeinc.webapi.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.snakeinc.webapi.entity.Player;

@Service
public class PlayerService {
    private final Map<Integer, Player> players = new HashMap<>();
    public PlayerService() {
    }
    public Player getPlayer(int id) {
        return players.get(id);
    }
    public Player createPlayer(Player player) {
        players.put(player.getId(), player);
        return getPlayer(player.getId());
    }
}
