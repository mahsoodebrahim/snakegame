package main.java;

import com.codegym.engine.cell.Game;
import com.codegym.engine.cell.Color;

public class Apple extends Fruit {
    public Apple(int x, int y) {
        super(x, y, "🍎");
    }

    @Override
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, symbol, Color.RED, 70);
    }
}
