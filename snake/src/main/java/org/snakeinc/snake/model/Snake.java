package org.snakeinc.snake.model;

import java.util.ArrayList;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

public class Snake implements GameObject {

    private final ArrayList<Tile> body;

    public Snake() {
        body = new ArrayList<>();
        Tile head = Grid.getInstance().getTile(5, 5);
        body.add(head);
        head.getGameObjectsInTile().add(this);
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public Tile getHead() {
        return body.getFirst();
    }

    public void eat(Apple apple) {
        body.add(apple.getTile());
        apple.getTile().getGameObjectsInTile().add(this);
        Basket.getInstance().removeApple(apple);
    }

    public void move(char direction) throws OutOfPlayException, SelfCollisionException {
        int x = getHead().getX();
        int y = getHead().getY();
        switch (direction) {
            case 'U':
                y--;
                break;
            case 'D':
                y++;
                break;
            case 'L':
                x--;
                break;
            case 'R':
                x++;
                break;
        }
        Tile newHead = Grid.getInstance().getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.gameObjectsInTile.contains(this)) {
            throw new SelfCollisionException();
        }
        newHead.getGameObjectsInTile().add(this);
        body.addFirst(newHead);
        body.getLast().getGameObjectsInTile().remove(this);
        body.removeLast();
        // Eat apples :
        for (GameObject gameObject : new ArrayList<>(newHead.getGameObjectsInTile())) {
            if (gameObject instanceof Apple) {
                this.eat((Apple) gameObject);
            }
        }

    }
}
