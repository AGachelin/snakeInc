package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.Direction;
public class SnakeTest {

    Game game = new Game(true);

    @Test
    public void snakeEatApplesAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        game.getBasket().addFood(game.getGrid().getTile(5, 4),null);
        game.iterate(Direction.UP);
        Assertions.assertEquals(2, game.getSnakeSize());
    }

    @Test
    void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        game.iterate(Direction.UP);
        Assertions.assertEquals(5, game.getSnakeHead().getX());
        Assertions.assertEquals(4, game.getSnakeHead().getY());
    }

    @Test
    void snakeMovesOutOfGame_ThrowsOutOfPlay() {
        Assertions.assertThrows(OutOfPlayException.class, () -> {
            for(int i = 0; i<6;i++) {
                game.iterate(Direction.UP);
            }
        });
    }
    @Test
    void testSelfCollision() {
        Assertions.assertThrows(SelfCollisionException.class, () -> {
            game.getBasket().addFood(game.getGrid().getTile(5, 4), null);
            game.iterate(Direction.UP);
            game.getBasket().addFood(game.getGrid().getTile(6, 4), null);
            game.iterate(Direction.RIGHT);
            game.getBasket().addFood(game.getGrid().getTile(6, 5), null);
            game.iterate(Direction.DOWN);
            game.getBasket().addFood(game.getGrid().getTile(5, 5), null);
            game.iterate(Direction.LEFT);
        });
    }

}
