package org.snakeinc.snake.ui;

import java.awt.Graphics;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.Tile;

public class GridComponent implements Drawable {

    private static GridComponent instance;

    private GridComponent() {
    }

    public static GridComponent getInstance() {
        if (instance == null) {
            instance = new GridComponent();
        }
        return instance;
    }

    @Override
    public void draw(Graphics g) {
        for (Tile tile : Grid.getInstance().getTiles().values()) {
            if (!tile.getGameObjectsInTile().isEmpty()) {
                new TileComponent(tile, tile.getX() * GamePanel.TILE_PIXEL_SIZE,
                        tile.getY() * GamePanel.TILE_PIXEL_SIZE).draw(g);
            }
        }
    }


}
