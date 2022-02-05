package fr.nathan.tetris.model.square;

import com.badlogic.gdx.graphics.g2d.Batch;
import fr.nathan.tetris.Rotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareGroup extends Square {

    private final List<SquarePart> squares;
    private final Pattern pattern;

    public SquareGroup(int x, int y, Pattern pattern) {
        super(x, y);
        this.pattern = pattern;
        squares      = new ArrayList<>(pattern.getBlocksPositions().length);

        int i = 0;
        // parts = {{0,0}, {0,1}, ...}
        for (int[] offsets : pattern.getBlocksPositions()) {
            int xOffset = offsets[0];
            int yOffset = offsets[1];
            SquarePart part = new SquarePart(x - xOffset, y - yOffset);
//            if (i == pattern.getRotationPointIndex()) {
//                part.initializeTexture(Color.WHITE);
//            }
//            else {
//                part.initializeTexture(new Color(
//                        Tetris.random.nextFloat(),
//                        Tetris.random.nextFloat(),
//                        Tetris.random.nextFloat(),
//                        1
//                ));
            part.initializeTexture(pattern.getColor());
//            }
            squares.add(part);
            i++;
        }
    }

    public Collection<SquarePart> getSquares() {
        return squares;
    }

    @Override
    public boolean canMoveRight() {
        for (SquarePart square : squares) {
            if (!square.canMoveRight()) return false;
        }
        return true;
    }

    @Override
    public boolean canMoveLeft() {
        for (SquarePart square : squares) {
            if (!square.canMoveLeft()) return false;
        }
        return true;
    }

    @Override
    public boolean canMoveDown() {
        for (SquarePart square : squares) {
            if (!square.canMoveDown()) return false;
        }
        return true;
    }

    @Override
    public void draw(Batch batch) {
        for (SquarePart square : squares) {
            square.draw(batch);
        }
    }

    @Override
    public void moveRight() {
        for (SquarePart square : squares) {
            square.moveRight();
        }
    }

    @Override
    public void moveLeft() {
        for (SquarePart square : squares) {
            square.moveLeft();
        }
    }

    @Override
    public void moveDown() {
        for (SquarePart square : squares) {
            square.moveDown();
        }
    }

    public void rotate(Rotation rotation) {
        Square middleSquare = squares.get(pattern.getRotationPointIndex());

        for (Square square : squares) {
            int[] differences = new int[]{
                    square.getX() - middleSquare.getX(),
                    square.getY() - middleSquare.getY(),
            };

            if (rotation == Rotation.RIGHT) {
                square.x -= differences[0];
                square.x -= differences[1];

                square.y -= differences[1];
                square.y += differences[0];

            }
            else {
                square.x -= differences[0];
                square.x += differences[1];

                square.y -= differences[1];
                square.y -= differences[0];

            }
        }
    }

    @Override
    public String toString() {
        return "SquareGroup{" +
                "squares=" + squares +
                '}';
    }
}
