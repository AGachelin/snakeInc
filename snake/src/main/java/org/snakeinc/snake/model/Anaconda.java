package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionException;

public final class Anaconda extends Snake{
    Anaconda(FoodEatenListener listener, Grid grid) {
        super(listener, grid, "Gray");
    }
    public void eat(Food food, Cell cell) throws MalnutritionException{
        if(food.getClass().getSimpleName().equals("Brocoli")) {
            if(!food.isSteamed){score+=1;}
            if(super.getSize()-2<=0){
                throw new MalnutritionException();
            }
            for(int i=0; i<2; i++) {
                Cell cell1 = super.getBody().getLast();
                cell1.removeSnake();
                super.getBody().removeLast();
            }
        }
        else{
            if(!food.isPoisoned){score+=2;}
            super.getBody().addFirst(cell);
            cell.addSnake(this);
        }
        super.getOnFoodEatenListener().onFoodEaten(food, cell);
    }
}
