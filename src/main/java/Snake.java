package main.java;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;

public class Snake {
    private static final String snakeHeadSymbol = "🐍";
    private static final String snakeBodySymbol = "🔸️";
    private Color snakeColor = Color.BLUE;
    private Direction direction = Direction.LEFT;

    private final ArrayList<SnakePart> snakeParts = new ArrayList<>(); // Represents the snake parts

    public Snake() {
        int snakeHeadX = SnakeGame.WIDTH / 2;
        int snakeHeadY = SnakeGame.HEIGHT / 2;

        // Initialize snake with 3 elements initially
        snakeParts.add(new SnakePart(snakeHeadX, snakeHeadY, snakeHeadSymbol, snakeColor));
        snakeParts.add(new SnakePart(snakeHeadX + 1, snakeHeadY, snakeBodySymbol, snakeColor));
        snakeParts.add(new SnakePart(snakeHeadX + 2, snakeHeadY, snakeBodySymbol, snakeColor));
    }

    public void draw(Game game) {
        for (SnakePart snakePart : snakeParts) {
            snakePart.draw(game);
        }
    }

    public void move() {
        // Get current snake head
        SnakePart currentSnakeHead = snakeParts.get(0);

        // Create a new snake head in the direction the snake is traveling
        SnakePart newSnakeHead = createNewSnakeHead(currentSnakeHead);

        // Check if newly created head causes snake to be out of bounds
        if (isSnakeOutOfBounds(newSnakeHead)) {
            return;
        }

        // Update current head symbol to a snake body symbol
        currentSnakeHead.symbol = snakeBodySymbol;

        // Add new snake head to beginning on snakeParts list
        snakeParts.add(0, newSnakeHead);

        // Remove last element of snakeParts list
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean isSnakeOutOfBounds(SnakePart snakeHead) {
        // Snake if out of bounds conditions
        return snakeHead.x < 0 || snakeHead.x >= SnakeGame.WIDTH || snakeHead.y < 0 || snakeHead.y >= SnakeGame.HEIGHT;
    }

    // Create a new snake part in the direction the snake is traveling
    public SnakePart createNewSnakeHead(SnakePart currentSnakeHead) {
        if (direction == Direction.UP) {
            return new SnakePart(currentSnakeHead.x, currentSnakeHead.y + 1, snakeHeadSymbol, snakeColor);
        } else if (direction == Direction.RIGHT) {
            return new SnakePart(currentSnakeHead.x + 1, currentSnakeHead.y, snakeHeadSymbol, snakeColor);
        } else if (direction == Direction.DOWN) {
            return new SnakePart(currentSnakeHead.x, currentSnakeHead.y - 1, snakeHeadSymbol, snakeColor);
        } else {
            return new SnakePart(currentSnakeHead.x - 1, currentSnakeHead.y, snakeHeadSymbol, snakeColor);
        }
    }
}
