package org.snakeinc.snake.model;
import lombok.SneakyThrows;
import org.snakeinc.snake.exception.MalnutritionException;

public final class BoaConstrictor extends Snake{
    BoaConstrictor(AppleEatenListener listener, Grid grid) {
        super(listener, grid, "Blue");
    }
    @SneakyThrows
    public void eat(Apple apple, Cell cell) {
        super.getBody().removeLast();
        if(super.getSize()<=0){
            throw new MalnutritionException();
        }
        cell.addSnake(this);
        super.getOnAppleEatenListener().onAppleEaten(apple, cell);    }
}
