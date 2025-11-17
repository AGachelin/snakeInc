package org.snakeinc.snake.model;

public final class Python extends Snake{
    Python(AppleEatenListener listener, Grid grid) {
        super(listener, grid, "Green");
    }

    public void eat(Apple apple, Cell cell) {
        super.getOnAppleEatenListener().onAppleEaten(apple, cell);
    }
}
