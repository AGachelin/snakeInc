package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Mode;

import static java.lang.Math.*;

@Data
public class Basket {

    private Grid grid;
    private List<Food> foods;
    private Mode mode;

    public Basket(Grid grid) {
        foods = new ArrayList<>();
        this.grid = grid;
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                mode = Mode.Random;
                break;
            case 1:
                mode = Mode.Easy;
                break;
            case 2:
                mode = Mode.Hard;
                break;
        }
    }

    public void addFood(Cell cell, Cell head) {
        if (cell == null) {
            int x = head.getX();
            int y = head.getY();
            if(this.mode == Mode.Random) {
                var random = new Random();
                do {
                    cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
                } while(cell.containsASnake());
            }
            else if(this.mode == Mode.Easy) {
                var random = new Random();
                do {
                    cell = grid.getTile(random.nextInt(max(0,x-5), min(x+5,GameParams.TILES_X)), random.nextInt(max(0, y-5), min(y+5,GameParams.TILES_Y)));
                } while(cell.containsASnake());
            }
            else {
                var random = new Random();
                do {
                    cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
                } while(cell.containsASnake() || pow((pow(x,2)+pow(y,2)),0.5)<30);
            }
        }
        Food food = FoodFactory.createFoodInCell(cell);
        foods.add(food);
    }

    public void removeFoodInCell(Food food, Cell cell) {
        cell.removeFood();
        foods.remove(food);
    }

    public boolean isEmpty() {
        return foods.isEmpty();
    }

    private void refill(int nFoods, Cell head) {
        for (int i = 0; i < nFoods; i++) {
            addFood(null, head);
        }
    }

    public void refillIfNeeded(int nFoods, Cell head) {
        int missingFood = nFoods - foods.size();
        if (missingFood > 0) {
            refill(missingFood, head);
        }
    }

}
