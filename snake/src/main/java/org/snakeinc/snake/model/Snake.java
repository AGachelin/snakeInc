package org.snakeinc.snake.model;

import java.util.ArrayList;

import lombok.Getter;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.State;

abstract sealed class Snake permits Anaconda, Python, BoaConstrictor{
    @Getter
    private final ArrayList<Cell> body;
    @Getter
    private final FoodEatenListener onFoodEatenListener;
    @Getter
    protected State state = State.GoodHealth;
    private final Grid grid;
    @Getter
    protected final String color;
    static int score = 0;

    public Snake(FoodEatenListener listener, Grid grid, String color) {
        this.body = new ArrayList<>();
        this.onFoodEatenListener = listener;
        this.grid = grid;
        Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        head.addSnake(this);
        body.add(head);

        Cell cell1 = grid.getTile(GameParams.SNAKE_DEFAULT_X - 1, GameParams.SNAKE_DEFAULT_Y);
        cell1.addSnake(this);
        body.add(cell1);

        Cell cell2 = grid.getTile(GameParams.SNAKE_DEFAULT_X - 2, GameParams.SNAKE_DEFAULT_Y);
        cell2.addSnake(this);
        body.add(cell2);

        this.color = color;
    }

    public int getSize() {
        return body.size();
    }

    public Cell getHead() {
        return body.getFirst();
    }

    public void eat(Food food, Cell cell) throws MalnutritionException {
        if(food.isPoisoned){
            if(state == State.GoodHealth){
                state=State.Poisoned;
            }
            else{
                state = State.PermanentlyDamaged;
            }
        }
        else if(food.getClass().getSimpleName().equals("Brocoli")){
            state=State.GoodHealth;
        }
    };

    public void move(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        int x = getHead().getX();
        int y = getHead().getY();
        switch (direction) {
            case Direction.UP:
                y--;
                break;
            case Direction.DOWN:
                y++;
                break;
            case Direction.LEFT:
                x--;
                break;
            case Direction.RIGHT:
                x++;
                break;
        }
        Cell newHead = grid.getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.containsASnake()) {
            throw new SelfCollisionException();
        }

        // Eat apple :
        if (newHead.containsFood()) {
            this.eat(newHead.getFood(), newHead);
            return;
        }

        // The snake did not eat :
        newHead.addSnake(this);
        body.addFirst(newHead);

        body.getLast().removeSnake();
        body.removeLast();

    }
}
