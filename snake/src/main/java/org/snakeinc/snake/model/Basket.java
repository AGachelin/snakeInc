package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Basket {

    private List<Apple> apples;

    private static Basket instance;

    private Basket() {
        apples = new ArrayList<>();
    }

    public static Basket getInstance() {
        if (instance == null) {
            instance = new Basket();
        }
        return instance;
    }

    public void addApple() {
        Apple newApple = new Apple();
        newApple.getTile().gameObjectsInTile.add(newApple);
        apples.add(newApple);
    }

    public void removeApple(Apple apple) {
        apples.remove(apple);
        apple.getTile().gameObjectsInTile.remove(apple);
    }

    public boolean isEmpty() {
        return apples.isEmpty();
    }

    public void refill(int nApples) {
        for (int i = 0; i < nApples; i++) {
            addApple();
        }
    }

    public void refillIfEmptyOrPartial(int nApples) {
        int missingApple = nApples - apples.size();
        if (missingApple > 0) {
            refill(missingApple);
        }
    }

}
