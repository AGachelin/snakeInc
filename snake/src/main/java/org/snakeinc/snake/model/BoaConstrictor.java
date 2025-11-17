package org.snakeinc.snake.model;
import org.snakeinc.snake.exception.MalnutritionException;

public final class BoaConstrictor extends Snake{
    BoaConstrictor(FoodEatenListener listener, Grid grid) {
        super(listener, grid, "Blue");
    }
    public void eat(Food food, Cell cell) throws MalnutritionException{
        if(food.getClass().getSimpleName().equals("Apple")) {;
            if(super.getSize()<0){
                throw new MalnutritionException();
            }
            cell.addSnake(this);
        }
        super.getOnFoodEatenListener().onFoodEaten(food, cell);
    }
}
