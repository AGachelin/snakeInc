package org.snakeinc.snake.model;

import java.util.Random;

public class FoodFactory {

    public static Food createFoodInCell(Cell cell) {
        var Random = new Random();
        Food food;
        if(Random.nextBoolean()) {
            food = new Apple();
        }
        else{
            food = new Brocoli();
        }
        cell.addFood(food);
        return food;
    }

}
