package org.snakeinc.webapi.controller;

import org.snakeinc.webapi.entity.Score;
import org.snakeinc.webapi.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/scores")
class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    
    @PostMapping("/")
    public Score createScore(@Valid @RequestBody Score score) {
        return this.scoreService.createScore(score);
    }
    
    @GetMapping("/")
    public Object getScores() {
        return this.scoreService.getScores();
    }
    
    @GetMapping("/stats")
    public Object getStats() {
        return this.scoreService.getStats();
    }
}
