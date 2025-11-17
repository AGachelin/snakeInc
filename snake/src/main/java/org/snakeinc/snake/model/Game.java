package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

import java.util.Random;

@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private Snake snake;
    @Getter
    private Boolean test;

    public Game(Boolean test) {
        test = test != null ? test : false;
        grid = new Grid();
        basket = new Basket(grid);
        basket.refillIfNeeded(1);
        var random = new Random();
        if(test) {
            this.snake = new Anaconda((food, cell) -> basket.removeFoodInCell(food, cell), grid);
        }
        else {
            switch (random.nextInt(0, 3)) {
                case 0:
                    this.snake = new Anaconda((food, cell) -> basket.removeFoodInCell(food, cell), grid);
                    break;
                case 1:
                    this.snake = new BoaConstrictor((food, cell) -> basket.removeFoodInCell(food, cell), grid);
                    break;
                case 2:
                    this.snake = new Python((food, cell) -> basket.removeFoodInCell(food, cell), grid);
                    break;
            }
        }
    }

    public void iterate(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        snake.move(direction);
        basket.refillIfNeeded(1);
    }

    public Integer getSnakeSize() {
        return snake.getSize();
    }

    public Cell getSnakeHead() {
        return snake.getHead();
    }


}
