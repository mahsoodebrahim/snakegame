package main.java;

import com.codegym.engine.cell.Game;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected String symbol;

    public GameObject(int x, int y, String symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    abstract public void draw(Game game);
}
