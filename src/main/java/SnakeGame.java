package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    public static void main(String[] args) {
        Game.launch();
    }

    @Override
    public void initialize() {
        createGame();
    }

    public void createGame() {
        drawScreen();
    }

    private void drawScreen() {
        setScreenSize(WIDTH, HEIGHT);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GREEN);
            }
        }
    }
}
