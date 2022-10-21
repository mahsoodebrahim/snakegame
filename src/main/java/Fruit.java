package main.java;

public abstract class Fruit extends GameObject {
    private boolean isAlive;

    public Fruit(int x, int y, String symbol) {
        super(x, y, symbol);

        // Set isAlive to true by default
        this.isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
