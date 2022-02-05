package fr.nathan.tetris.model.square;

import com.badlogic.gdx.graphics.g2d.Batch;
import fr.nathan.tetris.Tetris;

public class SquarePart extends Square {

    public SquarePart(int x, int y) {
        super(x, y);
    }

    @Override
    public void moveDown() {
        y--;
    }

    @Override
    public boolean canMoveDown() {
        return y != 0;
    }

    @Override
    public boolean canMoveLeft() {
        return x != 0;
    }

    @Override
    public boolean canMoveRight() {
        return x != Tetris.COLUMNS_COUNT - 1;
    }

    @Override
    public void draw(Batch batch) {
        if (y >= Tetris.LINES_COUNT) return;
        float squareLength = Tetris.squareWidth();
        float squareHeight = Tetris.squareHeight();

        batch.draw(getTexture(),
                x * squareLength + Tetris.MARGIN + Tetris.GAME_BORDER_WIDTH,
                y * squareHeight + Tetris.MARGIN + Tetris.GAME_BORDER_WIDTH,
                squareLength - Tetris.MARGIN * 2,
                squareHeight - Tetris.MARGIN * 2);
    }
}
