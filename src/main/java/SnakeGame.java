package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;
import com.codegym.engine.cell.Key;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL_SNAKE_LENGTH = 10;
    private static final String WINNING_MESSAGE = "YOU WIN! 🎉";
    private static final String LOSING_MESSAGE = "YOU LOSE! ☹️";

    private Apple apple;
    private Snake snake;
    private int gameSpeed;
    private boolean isGameOver;


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

        // Apple has been eaten
        if (!apple.isAlive()) {
            // Decrease game speed to make snake move faster
            gameSpeed -= 20;
            setTurnTimer(gameSpeed);

            // Reset apple
            apple.setAlive(true);
            apple = createNewApple();
        }

        // Check game ending conditions
        if (!snake.isAlive()) {
            gameOver(LOSING_MESSAGE);
        } else if (snake.length() == GOAL_SNAKE_LENGTH) {
            gameOver(WINNING_MESSAGE);
        }

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
        // Set the game as running
        isGameOver = false;

        // Create Snake
        snake = new Snake();

        // Create Apple
        // createNewApple method requires the snake object to be defined
        // which is why the snake object is created first
        apple = createNewApple();

        // Initial game speed
        gameSpeed = 300;
        setTurnTimer(gameSpeed);

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

    public void gameOver(String gameEndingMessage) {
        // Indicate the game has ended
        isGameOver = true;

        // Stop turn timer
        stopTurnTimer();

        // Show winning or losing message
        showMessageDialog(Color.NONE, gameEndingMessage, Color.DARKKHAKI, 80);
    }
}
