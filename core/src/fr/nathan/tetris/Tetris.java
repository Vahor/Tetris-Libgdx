package fr.nathan.tetris;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import fr.nathan.tetris.controller.SquareController;

import java.util.Random;

public class Tetris {

    public static final int LINES_COUNT = 20;
    public static final int COLUMNS_COUNT = 10;
    public static final float MARGIN = 1f;
    public static final float MOVE_DOWN_DELAY = .2f;

    public static float GAME_BORDER_WIDTH = 20;
    public static float GAME_BORDER_WIDTH_2 = GAME_BORDER_WIDTH / 2;
    public static float INFORMATION_AREA_WIDTH = 200;

    public static Color TEXT_COLOR = new Color(1, 1, 1, 1);
    public static Color PRIMARY_COLOR = new Color(0.4745098f, 0.4745098f, 0.4745098f, 1);
    public static Color SECONDARY_COLOR = new Color(0.63137255f, 0.89411765f, 0.98823529f, 1);

    public static Client client;
    public static Batch batch;
    public static Random random;

    public static boolean lose = false;

    public static SquareController squareController;

    public static float informationAreaStart() {
        return client.getWidth() - INFORMATION_AREA_WIDTH;
    }

    public static float squareWidth() {
        return (client.getWidth() - INFORMATION_AREA_WIDTH - 2 * GAME_BORDER_WIDTH) / COLUMNS_COUNT;
    }

    public static float squareHeight() {
        return (client.getHeight() - 2 * GAME_BORDER_WIDTH) / LINES_COUNT;
    }
}
