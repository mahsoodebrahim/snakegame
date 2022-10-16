package main.java;

import com.codegym.engine.cell.Game;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected boolean isAlive;
    protected String symbol;

    public GameObject(int x, int y, String symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;

        // Set by default, GameObject is alive (true)
        isAlive = true;
    }

    abstract public void draw(Game game);
}
