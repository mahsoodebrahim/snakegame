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

    public boolean collidesWith(Fruit ...otherFruits) {
        for (Fruit otherFruit : otherFruits) {
            if (otherFruit == null) continue;

            if (this.x == otherFruit.x && this.y == otherFruit.y) return true;
        }

        return false;
    }
}
