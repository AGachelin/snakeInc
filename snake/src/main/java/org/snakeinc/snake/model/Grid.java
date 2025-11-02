package org.snakeinc.snake.model;

import java.util.HashMap;
import lombok.Data;

@Data
public class Grid {

    public static Integer TILES_X = 30;
    public static Integer TILES_Y = 30;
    private HashMap<CellKey, Tile> tiles = new HashMap<>();

    private static Grid instance;

    private Grid() {
        for (int x = 0; x < Grid.TILES_X; x++) {
            for (int y = 0; y < Grid.TILES_Y; y++) {
                tiles.put(new CellKey(x, y), new Tile(x, y));
            }
        }
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public Tile getTile(int x, int y) {
        return tiles.get(new CellKey(x, y));
    }


    private record CellKey(int x, int y) {

    }

}
