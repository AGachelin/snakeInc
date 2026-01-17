package org.snakeinc.webapi.controller;

import jakarta.validation.Valid;
import org.snakeinc.webapi.service.PlayerService;
import org.springframework.web.bind.annotation.*;
import org.snakeinc.webapi.entity.Player;

@RestController
@RequestMapping("/api/v1/players")
class PlayerController {
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable String id) {
        Integer playerId = Integer.parseInt(id);
        return this.playerService.getPlayer(playerId);
    }
    @PostMapping("/")
    public Player createPlayer(@Valid @RequestBody Player player) {
        return this.playerService.createPlayer(player);
    }
}
