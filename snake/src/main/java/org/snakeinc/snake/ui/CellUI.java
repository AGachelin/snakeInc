package org.snakeinc.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.AllArgsConstructor;
import org.snakeinc.snake.model.Cell;

@AllArgsConstructor
public class CellUI {

    private Cell cell;
    private int upperPixelX;
    private int upperPixelY;

    public void drawRectangle(Graphics g) {
        g.fillRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
        Graphics2D g2 = (Graphics2D) g;
        if(cell.getColor()!=null){
        switch(cell.getColor()) {
            case "Green":
                g2.setColor(Color.GREEN.darker());
                break;
            case "Blue":
                g2.setColor(Color.BLUE.darker());
                break;
            case "Gray":
                g2.setColor(Color.GRAY.darker());
                break;
        }}
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void drawOval(Graphics g) {
        g.fillOval(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void draw(Graphics g) {

        if (cell.containsAnApple()) {
            g.setColor(Color.RED);
            drawOval(g);
        }
        if (cell.containsASnake()) {
            if(cell.getColor() != null){
            switch(cell.getColor()) {
                case "Green":
                    g.setColor(Color.GREEN);
                    break;
                case "Blue":
                    g.setColor(Color.BLUE);
                    break;
                case "Gray":
                    g.setColor(Color.GRAY);
                    break;
            }}
            drawRectangle(g);
        }

    }

}
