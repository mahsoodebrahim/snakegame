package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class Grape extends Fruit {
    public Grape(int x, int y) {
        super(x, y, "🍇");
    }

    @Override
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, symbol, Color.PURPLE, 70);
    }
}
