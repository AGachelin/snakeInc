package org.snakeinc.webapi.service;

import org.snakeinc.webapi.entity.Score;
import org.snakeinc.webapi.entity.SnakeName;
import org.snakeinc.webapi.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final CrudRepository<Score, Integer> scores;
    private final PlayerService playerService;

    @Autowired
    public ScoreService(CrudRepository<Score, Integer> scores, PlayerService playerService) {
        this.scores = scores;
        this.playerService = playerService;
    }

    public Score getScore(int id) {
        return scores.findById(id).orElse(null);
    }

    public Score createScore(Score score) {
        Player player = playerService.getPlayer(score.getPlayerId());
        if (player == null) {
            throw new IllegalArgumentException("Player " + score.getPlayerId() + " not found");
        }
        return this.scores.save(score);
    }

    public List<Score> getScores() {
        return (List<Score>) this.scores.findAll();
    }

    public List<Map<String, Object>> getStats() {
        List<Score> allScores = (List<Score>) this.scores.findAll();
        
        Map<Integer, Map<SnakeName, List<Score>>> groupedByPlayerAndSnake = allScores.stream()
                .collect(Collectors.groupingBy(
                        Score::getPlayerId,
                        Collectors.groupingBy(Score::getSnake)
                ));
        
        return groupedByPlayerAndSnake.entrySet().stream()
                .map(playerEntry -> {
                    int playerId = playerEntry.getKey();
                    Map<SnakeName, List<Score>> snakeScores = playerEntry.getValue();
                    
                    List<Map<String, Object>> stats = snakeScores.entrySet().stream()
                            .map(snakeEntry -> {
                                List<Score> scores = snakeEntry.getValue();
                                int min = scores.stream().mapToInt(Score::getScore).min().orElse(0);
                                int max = scores.stream().mapToInt(Score::getScore).max().orElse(0);
                                double average = scores.stream().mapToInt(Score::getScore).average().orElse(0);
                                
                                Map<String, Object> stat = new LinkedHashMap<>();
                                stat.put("snake", snakeEntry.getKey());
                                stat.put("min", min);
                                stat.put("max", max);
                                stat.put("average", average);
                                return stat;
                            })
                            .toList();
                    
                    Map<String, Object> playerStat = new LinkedHashMap<>();
                    playerStat.put("playerId", playerId);
                    playerStat.put("stats", stats);
                    return playerStat;
                })
                .toList();
    }
}
