package org.snakeinc.snake.model;

import lombok.Getter;

import java.util.Random;

@Getter
public final class Brocoli extends Food{
    public Brocoli() {
        var Random = new Random();
        this.isSteamed = Random.nextBoolean();
    }

}