package main.java;

import com.codegym.engine.cell.Game;
import com.codegym.engine.cell.Color;

public class Apple extends GameObject {
    private boolean isAlive;

    public Apple(int x, int y) {
        super(x, y, "🍎");

        this.isAlive = true;
    }

    @Override
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, symbol, Color.RED, 70);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
