package org.snakeinc.webapi.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import org.snakeinc.webapi.entity.Player;

@Service
public class PlayerService {
    private final CrudRepository<Player, Integer> players;
    public PlayerService(CrudRepository<Player, Integer> players) {
        this.players = players;
    }
    public Player getPlayer(int id) {
        return players.findById(id).orElse(null);
    }
    public Player createPlayer(Player player) {
        players.save(player);
        return getPlayer(player.getId());
    }
}
