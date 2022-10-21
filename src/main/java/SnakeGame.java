package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;
import com.codegym.engine.cell.Key;

import java.util.ArrayList;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL_SNAKE_LENGTH = 10;
    private static final int APPLE_REWARD_POINTS = 5;
    private static final int GRAPE_REWARD_POINTS = 15;
    private static final String WINNING_MESSAGE = "YOU WIN! 🎉";
    private static final String LOSING_MESSAGE = "YOU LOSE! ☹️";
    public static final String APPLE = "APPLE";
    public static final String GRAPE = "GRAPE";


    private Apple apple;
    private Grape grape;
    private Snake snake;
    private int gameSpeed;
    private boolean isGameOver;
    private int gameScore;
    private ArrayList<Fruit> fruits;


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
        snake.move(apple, grape);

        // Apple has been eaten
        if (!apple.isAlive()) {
            // Decrease game speed to make snake move faster
            gameSpeed -= 20;
            setTurnTimer(gameSpeed);

            // Reset apple
            fruits.remove(apple);
            apple.setAlive(true);
            apple = (Apple) createNewFruit(APPLE);

            // Increase score
            gameScore += APPLE_REWARD_POINTS;
            setScore(gameScore);
        }

        // Grape has been eaten
        if (!grape.isAlive()) {
            // Decrease game speed to make snake move faster
            gameSpeed -= 20;
            setTurnTimer(gameSpeed);

            // Reset grape
            fruits.remove(grape);
            grape.setAlive(true);
            grape = (Grape) createNewFruit(GRAPE);


            // Increase score
            gameScore += GRAPE_REWARD_POINTS;
            setScore(gameScore);
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
        } else if (key == Key.SPACE) {
            if (isGameOver) createGame();
        }
    }

    public void createGame() {
        // Set the game as running
        isGameOver = false;

        // Create Snake
        snake = new Snake();

        // Create fruits array
        fruits = new ArrayList<>();

        // Create Fruit
        // createNewFruit(FruitName) method requires the snake object and fruits array
        // to be defined which is why they are created first
        apple = (Apple) createNewFruit(APPLE);
        grape = (Grape) createNewFruit(GRAPE);

        // Add fruit to fruits array
        fruits.add(apple);
        fruits.add(grape);

        // Initial game speed
        gameSpeed = 300;
        setTurnTimer(gameSpeed);

        // Initialize game score to start at 0
        gameScore = 0;
        setScore(gameScore);

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
        grape.draw(this);
        snake.draw(this);
    }

    private Fruit createNewFruit(String fruitType) {
        Fruit newFruit;

        do {
            int randX = getRandomNumber(WIDTH);
            int randY = getRandomNumber(HEIGHT);
            newFruit = createInstanceOfFruit(fruitType, randX, randY);
        } while (snake.collidesWith(newFruit) || fruitsCollidesWith(newFruit));

        return newFruit;
    }

    private Fruit createInstanceOfFruit(String fruitType, int x, int y) {
        switch (fruitType) {
            case APPLE:
                return new Apple(x, y);
            case GRAPE:
                return new Grape(x, y);
        }

        return null;
    }

    private boolean fruitsCollidesWith(Fruit newFruit) {
        for (Fruit fruit: fruits) {
            if (fruit.x == newFruit.x && fruit.y == newFruit.y) {
                return true;
            }
        }

        return false;
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
