package org.snakeinc.snake.model;

public abstract sealed class Food permits Apple, Brocoli {
    public Boolean isPoisoned = false;
    public Boolean isSteamed = false;
}
