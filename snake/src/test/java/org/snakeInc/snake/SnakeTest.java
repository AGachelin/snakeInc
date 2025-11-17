package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Game;

public class SnakeTest {

    Game game = new Game();

    @Test
    public void snakeEatApplesAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getSnake().move('U');
        Assertions.assertEquals(2, game.getSnake().getSize());
    }

    @Test
    void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException {
        game.getSnake().move('U');
        Assertions.assertEquals(5, game.getSnake().getHead().getX());
        Assertions.assertEquals(4, game.getSnake().getHead().getY());
    }

    @Test
    void snakeMovesOutOfGame_ThrowsOutOfPlay() throws OutOfPlayException, SelfCollisionException {
        Assertions.assertThrows(OutOfPlayException.class, () -> {
            for(int i = 0; i<6;i++) {
                game.iterate('U');
            }
        });
    }
    @Test
    void testOutOfPlay() throws OutOfPlayException, SelfCollisionException {
        Assertions.assertThrows(SelfCollisionException.class, () -> {
            game.getBasket().addApple(game.getGrid().getTile(5, 4));
            game.getSnake().move('U');
            game.getBasket().addApple(game.getGrid().getTile(6, 4));
            game.getSnake().move('R');
            game.getBasket().addApple(game.getGrid().getTile(6, 5));
            game.getSnake().move('D');
            game.getBasket().addApple(game.getGrid().getTile(5, 5));
            game.getSnake().move('L');
        });
    }

}
