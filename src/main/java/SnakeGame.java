package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;
import com.codegym.engine.cell.Key;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Apple apple;
    private Snake snake;

    public static void main(String[] args) {
        Game.launch();
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
//        snake.move();

        drawScreen();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.UP) {
            System.out.println(Key.UP);
        } else if (key == Key.RIGHT) {
            System.out.println(Key.RIGHT);
        } else if (key == Key.DOWN) {
            System.out.println(Key.DOWN);
        } else if (key == Key.LEFT) {
            System.out.println(Key.LEFT);
        }
    }

    public void createGame() {
        // Create Apple
        apple = new Apple(5, 5);

        // Create Snake
        snake = new Snake();

        // Initial game speed
        setTurnTimer(300);

        // Draw screen after game objects have been initialized
        drawScreen();
    }

    private void drawScreen() {
        // Clear screen
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.GREEN, "");
            }
        }

        apple.draw(this);
        snake.draw(this);
    }
}
