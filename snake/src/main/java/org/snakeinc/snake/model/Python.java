package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionException;

public final class Python extends Snake{
    Python(FoodEatenListener listener, Grid grid) {
        super(listener, grid, "Green");
    }
    public void eat(Food food, Cell cell) throws MalnutritionException{
        super.eat(food, cell);
        if(food.getClass().getSimpleName().equals("Brocoli")) {
            if(!food.isSteamed){score+=1;}
            if(super.getSize()-3<=0){
                throw new MalnutritionException();
            }
        }
        else if(!food.isPoisoned){score+=2;}
        super.getOnFoodEatenListener().onFoodEaten(food, cell);
    }
}
