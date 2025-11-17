package org.snakeinc.snake.model;
public final class Anaconda extends Snake{
    Anaconda(AppleEatenListener listener, Grid grid) {
        super(listener, grid, "Gray");
    }
    public void eat(Apple apple, Cell cell) {
        super.getBody().addFirst(cell);
        cell.addSnake(this);
        super.getOnAppleEatenListener().onAppleEaten(apple, cell);
    }
}
