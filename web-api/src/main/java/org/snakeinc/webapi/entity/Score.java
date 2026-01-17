package org.snakeinc.webapi.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Score {
    @Getter
    @Id
    private final int id;
    private static AtomicInteger nextId = new AtomicInteger();
    
    @Getter
    @Min(value = 0, message = "score should be positive")
    private final int score;
    
    @Getter
    private final LocalDateTime playedAt;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "playerId")
    private final Player player;
    
    @Getter
    @NotNull(message = "snake name is mandatory")
    @Pattern(regexp = "python|anaconda|boaConstrictor", message = "snake can only be: python, anaconda, or boaConstrictor")
    private final SnakeName snake;

    public Score() {
        this.id = nextId.getAndIncrement();
        this.playedAt = LocalDateTime.now();
        this.score = 0;
        this.player = null;
        this.snake = null;
    }

    public Score(int score, LocalDateTime playedAt, Player player, SnakeName snake) {
        this.score = score;
        this.playedAt = playedAt;
        this.player = player;
        this.snake = snake;
        this.id = nextId.getAndIncrement();
    }

    public int getPlayerId() {
        return player != null ? player.getId() : 0;
    }
}
