package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Apple apple;

    public static void main(String[] args) {
        Game.launch();
    }

    @Override
    public void initialize() {
        createGame();
    }

    public void createGame() {
        apple = new Apple(WIDTH / 2, HEIGHT / 2);

        // Draw screen after game objects have been initialized
        drawScreen();
    }

    private void drawScreen() {
        setScreenSize(WIDTH, HEIGHT);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GREEN);
            }
        }

        apple.draw(this);
    }
}
