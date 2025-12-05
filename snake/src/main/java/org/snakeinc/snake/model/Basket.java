package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import lombok.Getter;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Mode;

import static java.lang.Math.*;

@Data
public class Basket {
    private Grid grid;
    private List<Food> foods;
    private List<Cell> cells;
    private Mode mode;
    @Getter
    private boolean isCrazy;

    public Basket(Grid grid) {
        foods = new ArrayList<>();
        cells = new ArrayList<>();
        this.grid = grid;
        Random rand = new Random();
        this.isCrazy = rand.nextBoolean();
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

    public void addFood(Cell cell, Cell head, Food food) {
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
                } while(cell.containsASnake() || pow((pow(x- cell.getX(),2)+pow(y-cell.getY(),2)),0.5)<25);
            }
        }
        if(food==null) {
            food = FoodFactory.createFoodInCell(cell);
        }
        else{
            cell.addFood(food);
        }
        foods.add(food);
        cells.add(cell);
    }

    public void removeFoodInCell(Food food, Cell cell) {
        cell.removeFood();
        foods.remove(food);
        cells.remove(cell);
    }

    public boolean isEmpty() {
        return foods.isEmpty();
    }

    private void refill(int nFoods, Cell head) {
        for (int i = 0; i < nFoods; i++) {
            addFood(null, head, null);
        }
    }

    public void refillIfNeeded(int nFoods, Cell head) {
        int missingFood = nFoods - foods.size();
        if (missingFood > 0) {
            refill(missingFood, head);
        }
    }

    public void move(Cell head){
        Random rand = new Random();
        if(rand.nextInt(7)!=3) {
            return;
        }
        int x = head.getX();
        int y = head.getY();
        for(int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            double dist = pow((pow(x- cell.getX(),2)+pow(y-cell.getY(),2)),0.5);
            if(dist<3) {
                Food food = foods.get(i);
                removeFoodInCell(food, cell);
                addFood(null, head, food);
            }
        }
    }

}
