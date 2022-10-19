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
        snake.move(apple);

        drawScreen();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        } else if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        }
    }

    public void createGame() {
        // Create Snake
        snake = new Snake();

        // Create Apple
        // createNewApple method requires the snake object to be defined
        // which is why the snake object is created first
        apple = createNewApple();

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

    private Apple createNewApple() {
        Apple newApple;

        do {
            int randX = getRandomNumber(WIDTH);
            int randY = getRandomNumber(HEIGHT);
            newApple = new Apple(randX, randY);
        } while (snake.collidesWith(newApple));

        return newApple;
    }
}
