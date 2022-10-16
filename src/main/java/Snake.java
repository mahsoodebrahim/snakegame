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
        for (SnakePart snakePart: snakeParts) {
            snakePart.draw(game);
        }
    }

    public void move() {
        SnakePart curSnakeHead = snakeParts.get(0);
        curSnakeHead.symbol = snakeBodySymbol;
        SnakePart newSnakeHead = new SnakePart(curSnakeHead.x - 1, curSnakeHead.y, snakeHeadSymbol, snakeColor);
        snakeParts.add(0, newSnakeHead);

        snakeParts.remove(snakeParts.size() - 1);
    }
}
