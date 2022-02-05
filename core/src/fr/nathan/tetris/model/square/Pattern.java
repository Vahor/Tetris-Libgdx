package fr.nathan.tetris.model.square;

import com.badlogic.gdx.graphics.Color;

public class Pattern {

    private final int[][] blocksPositions;
    private final Color color;
    private final int rotationPointIndex;

    public Pattern(int[][] blocksPositions, Color color, int rotationPointIndex) {
        this.blocksPositions    = blocksPositions;
        this.color              = color;
        this.rotationPointIndex = rotationPointIndex;
    }

    public int[][] getBlocksPositions() {
        return blocksPositions;
    }

    public Color getColor() {
        return color;
    }

    public int getRotationPointIndex() {
        return rotationPointIndex;
    }
}
