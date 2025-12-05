package org.snakeinc.snake.model;
import org.snakeinc.snake.exception.MalnutritionException;

public final class BoaConstrictor extends Snake{
    BoaConstrictor(FoodEatenListener listener, Grid grid) {
        super(listener, grid, "Blue");
    }
    public void eat(Food food, Cell cell) throws MalnutritionException{
        super.eat(food, cell);
        if(food.getClass().getSimpleName().equals("Apple")) {
            if(!food.isPoisoned){score+=2;}
            if(super.getSize()<0){
                throw new MalnutritionException();
            }
            cell.addSnake(this);
        }
        else{if(!food.isSteamed){score+=1;}}
        super.getOnFoodEatenListener().onFoodEaten(food, cell);
    }
}
