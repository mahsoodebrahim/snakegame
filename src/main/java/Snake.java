package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;

public class Snake {
    private static final String snakeHeadSymbol = "🐍";
    private static final String snakeBodySymbol = "🔸️";
    private Color aliveSnakeColor = Color.BLUE;
    private Color deadSnakeColor = Color.BLACK;
    private Direction direction = Direction.LEFT;

    private final ArrayList<SnakePart> snakeParts = new ArrayList<>(); // Represents the snake parts
    private boolean isAlive = true;

    public Snake() {
        int snakeHeadX = SnakeGame.WIDTH / 2;
        int snakeHeadY = SnakeGame.HEIGHT / 2;

        // Initialize snake with 3 elements initially
        snakeParts.add(new SnakePart(snakeHeadX, snakeHeadY, snakeHeadSymbol, aliveSnakeColor));
        snakeParts.add(new SnakePart(snakeHeadX + 1, snakeHeadY, snakeBodySymbol, aliveSnakeColor));
        snakeParts.add(new SnakePart(snakeHeadX + 2, snakeHeadY, snakeBodySymbol, aliveSnakeColor));
    }

    public void draw(Game game) {
        for (SnakePart snakePart : snakeParts) {
            if (!isAlive) {
                snakePart.setColor(deadSnakeColor);
            }

            snakePart.draw(game);
        }
    }

    public void move(Apple apple) {
        // Get current snake head
        SnakePart currentSnakeHead = snakeParts.get(0);

        // Create a new snake head in the direction the snake is traveling
        SnakePart newSnakeHead = createNewSnakeHead(currentSnakeHead);

        // Check if newly created snake head causes snake to be out of bounds
        // Or if newly created snake head intercepts any snake body part
        if (isSnakeOutOfBounds(newSnakeHead) || collidesWith(newSnakeHead)) {
            isAlive = false;
            return;
        }

        // Update current head symbol to a snake body symbol
        currentSnakeHead.symbol = snakeBodySymbol;

        // Add new snake head to beginning on snakeParts list
        snakeParts.add(0, newSnakeHead);

        // Remove last element of snakeParts list if snake head and apple are not on the same coordinate
        if (snakeAteApple(apple, newSnakeHead)) {
            apple.setAlive(false);
        } else {
            snakeParts.remove(snakeParts.size() - 1);
        }
    }

    public boolean isSnakeOutOfBounds(SnakePart snakeHead) {
        // Snake if out of bounds conditions
        return snakeHead.x < 0 || snakeHead.x >= SnakeGame.WIDTH || snakeHead.y < 0 || snakeHead.y >= SnakeGame.HEIGHT;
    }

    // Create a new snake part in the direction the snake is traveling
    public SnakePart createNewSnakeHead(SnakePart currentSnakeHead) {
        if (direction == Direction.UP) {
            return new SnakePart(currentSnakeHead.x, currentSnakeHead.y - 1, snakeHeadSymbol, aliveSnakeColor);
        } else if (direction == Direction.RIGHT) {
            return new SnakePart(currentSnakeHead.x + 1, currentSnakeHead.y, snakeHeadSymbol, aliveSnakeColor);
        } else if (direction == Direction.DOWN) {
            return new SnakePart(currentSnakeHead.x, currentSnakeHead.y + 1, snakeHeadSymbol, aliveSnakeColor);
        } else {
            return new SnakePart(currentSnakeHead.x - 1, currentSnakeHead.y, snakeHeadSymbol, aliveSnakeColor);
        }
    }

    public void setDirection(Direction newDirection) {
        // Prevent illegal direction change
        SnakePart snakeHead = snakeParts.get(0);
        SnakePart snake1stBodyElement = snakeParts.get(1);

        if (direction == Direction.UP & (snakeHead.y == snake1stBodyElement.y)
                || direction == Direction.DOWN & (snakeHead.y == snake1stBodyElement.y)
                || direction == Direction.LEFT & (snakeHead.x == snake1stBodyElement.x)
                || direction == Direction.RIGHT & (snakeHead.x == snake1stBodyElement.x)) {
            return;
        }

        if (direction == Direction.UP && newDirection == Direction.DOWN
                || direction == Direction.DOWN && newDirection == Direction.UP
                || direction == Direction.LEFT && newDirection == Direction.RIGHT
                || direction == Direction.RIGHT && newDirection == Direction.LEFT) {
            return;
        }

        direction = newDirection;
    }

    public boolean snakeAteApple(Apple apple, SnakePart snakeHead) {
        return apple.x == snakeHead.x && apple.y == snakeHead.y;
    }

    public boolean collidesWith(GameObject gameObject) {
        for (SnakePart snakePart : snakeParts) {
            if (snakePart.x == gameObject.x
                    && snakePart.y == gameObject.y) {
                return true;
            }
        }

        return false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int length() {
        return snakeParts.size();
    }
}
