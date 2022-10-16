package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class SnakePart extends GameObject {
    private Color color;

    public SnakePart(int x, int y, String symbol, Color color) {
        super(x, y, symbol);

        this.color = color;
    }

    @Override
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, symbol, Color.BLUE, 80);
    }
}
