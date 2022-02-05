package fr.nathan.tetris.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import fr.nathan.tetris.Tetris;
import fr.nathan.tetris.model.square.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareController {

    private static final String TAG = "SquareController";

    private List<Pattern> patterns;

    public void loadPatterns() {
        if (patterns == null) {
            patterns = new ArrayList<>();

            FileHandle patternFolder = Gdx.files.internal("square");

            for (FileHandle patternFile : patternFolder.list()) {
                // ignore file
                if (patternFile.isDirectory() || !patternFile.extension().equalsIgnoreCase("square")) continue;

                Gdx.app.debug(TAG, "Found " + patternFile.name());
                String[] fileContent = patternFile.readString().split("\n");

                Pattern pattern = readPatterns(fileContent);
                if (pattern != null)
                    this.patterns.add(pattern);

            }
            Gdx.app.debug(TAG, "Found " + patterns.size() + " patterns");
        }
    }

    public Pattern getRandomPattern() {
        return patterns.get(Tetris.random.nextInt(patterns.size()));
    }

    public Pattern readPatterns(String[] fileContent) {
        if (fileContent.length == 0) return null;
        String[] colorData = fileContent[0].split(" ");
        int rotationPointIndex = Integer.parseInt(fileContent[1]);
        int squareCount = Integer.parseInt(fileContent[2]);
        int[][] blocksPosition = new int[squareCount][3];

        int offset = 3;
        int part = 0;
        // skip 2 lines as it is used for squareCount
        for (int i = offset; i < fileContent.length; i++) {
            String[] parts = fileContent[i].split("");
            for (int k = 0; k < parts.length; k++) {
                if (!parts[k].trim().isEmpty()) {
                    blocksPosition[part++] = new int[]{k, i - offset};
                }
            }
        }

        return new Pattern(
                blocksPosition,
                new Color(
                        Float.parseFloat(colorData[0]),
                        Float.parseFloat(colorData[1]),
                        Float.parseFloat(colorData[2]),
                        1
                ),
                rotationPointIndex
        );
    }
}
