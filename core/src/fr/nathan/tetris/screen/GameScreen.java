package fr.nathan.tetris.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import fr.nathan.tetris.Rotation;
import fr.nathan.tetris.Tetris;
import fr.nathan.tetris.model.grid.Layout;
import fr.nathan.tetris.model.infos.Informations;
import fr.nathan.tetris.model.square.Pattern;
import fr.nathan.tetris.model.square.Square;
import fr.nathan.tetris.model.square.SquareGroup;
import fr.nathan.tetris.model.square.SquarePart;
import fr.nathan.tetris.updater.TickUpdater;

import java.util.*;

import static fr.nathan.tetris.Tetris.batch;

public class GameScreen extends ScreenAdapter {

    private static final String TAG = "GameScreen";

    private SquareGroup currentSquare = null;

    private final Layout grid;
    private final Informations informations;
    private final TickUpdater moveDownUpdater;
    private final HashMap<Integer, List<Square>> deadSquareMapList = new HashMap<>(Tetris.LINES_COUNT);

    public GameScreen() {
        Tetris.squareController.loadPatterns();
        grid         = new Layout();
        informations = new Informations();


        moveDownUpdater = new TickUpdater(Tetris.MOVE_DOWN_DELAY) {
            @Override
            public void onUpdate() {
                currentSquare.moveDown();
            }
        };

        nextSquare();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && currentSquare.canMoveLeft() && !isCollidingWithDeadSquare(-1, 1)) {
            currentSquare.moveLeft();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && currentSquare.canMoveRight() && !isCollidingWithDeadSquare(1, 1)) {
            currentSquare.moveRight();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            currentSquare.rotate(Rotation.RIGHT);
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentSquare.rotate(Rotation.LEFT);
        }
    }

    private boolean isCollidingWithDeadSquare(int offsetX, int offsetY) {
        for (List<Square> list : deadSquareMapList.values()) {
            for (Square square : list) {
                // Impossible case
                if (square.getY() > currentSquare.getY()) continue;

                for (SquarePart squarePart : currentSquare.getSquares()) {
                    if (squarePart.isCollidingWith(square, offsetX, offsetY)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private void tryRemoveLine() {
        Set<Integer> yLevelsWhereWeRemovedStuff = new HashSet<>();
        for (SquarePart square : currentSquare.getSquares()) {
            List<Square> squares = deadSquareMapList.get(square.getY());
            if (squares.size() == Tetris.COLUMNS_COUNT) {
                squares.clear();
                yLevelsWhereWeRemovedStuff.add(square.getY());
            }
        }

        for (Integer integer : yLevelsWhereWeRemovedStuff) {
            for (int i = integer; i < Tetris.LINES_COUNT - 1; i++) {
                List<Square> lineOneUp = deadSquareMapList.get(i + 1);

                if (lineOneUp == null) lineOneUp = new ArrayList<>(Tetris.LINES_COUNT);

                for (Square square : lineOneUp) {
                    square.moveDown();
                }
                deadSquareMapList.put(i, lineOneUp);
            }
        }
    }

    private void checkFinished() {
        if (deadSquareMapList.containsKey(Tetris.LINES_COUNT - 1)) {
            boolean isSpawnFree = true;
            for (Square square : deadSquareMapList.get(Tetris.LINES_COUNT - 1)) {
                if (!isSpawnFree) continue;
                isSpawnFree = square.getX() == Tetris.COLUMNS_COUNT / 2;
            }
            Tetris.lose = !isSpawnFree;
        }
    }

    private void handleCollisions() {
        if (!currentSquare.canMoveDown() || isCollidingWithDeadSquare(0, 1)) {

            // Add currentSquare' squares to deadSquareList
            for (SquarePart square : currentSquare.getSquares()) {
                List<Square> previous = deadSquareMapList.get(square.getY());

                if (previous == null) previous = new ArrayList<>(Tetris.LINES_COUNT);
                previous.add(square);

                deadSquareMapList.put(square.getY(), previous);
            }

            tryRemoveLine();

            checkFinished();

            if (!Tetris.lose) {
                // Add new square
                nextSquare();
            }
        }
    }

    private void nextSquare() {
        Pattern randomPattern = Tetris.squareController.getRandomPattern();
        currentSquare = new SquareGroup(
                Tetris.COLUMNS_COUNT / 2,
                Tetris.LINES_COUNT - 1,
                randomPattern);
    }

    @Override
    public void render(float delta) {
        super.render(delta);


        if (!Tetris.lose) {
            informations.update();

            handleInput();

            handleCollisions();

            // Move currentSquare down
            moveDownUpdater.tick(delta);
        }


        batch.begin();
        grid.draw(batch);
        informations.draw(batch);
        currentSquare.draw(batch);
        for (List<Square> list : deadSquareMapList.values()) {
            for (Square square : list) {
                square.draw(batch);
            }
        }
        batch.end();
    }
}
