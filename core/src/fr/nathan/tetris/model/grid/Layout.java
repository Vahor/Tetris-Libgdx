package fr.nathan.tetris.model.grid;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import fr.nathan.tetris.Tetris;

public class Layout {

    private final Texture innerBorderTexture;
    private final Texture outerBorderTexture;

    public Layout() {

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Tetris.PRIMARY_COLOR);
        pixmap.fill();

        outerBorderTexture = new Texture(pixmap);

        pixmap.setColor(Tetris.SECONDARY_COLOR);
        pixmap.fill();

        innerBorderTexture = new Texture(pixmap);

        pixmap.dispose();

    }

    public void draw(Batch batch) {
        drawInnerBorders(batch);
        drawOuterBorders(batch);
        addBackgroundForInformations(batch);
        addNextSquareInformations(batch);
    }
    private void addNextSquareInformations(Batch batch) {

    }

    private void addBackgroundForInformations(Batch batch) {
        batch.draw(outerBorderTexture,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH,
                0,
                Tetris.INFORMATION_AREA_WIDTH,
                Tetris.client.getHeight()
        );
    }

    private void drawOuterBorders(Batch batch) {
        // left
        batch.draw(outerBorderTexture,
                0,
                0,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getHeight()
        );

        //top
        batch.draw(outerBorderTexture,
                0,
                Tetris.client.getHeight() - Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH,
                Tetris.GAME_BORDER_WIDTH_2
        );

        //right
        batch.draw(outerBorderTexture,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH - Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getHeight() - Tetris.GAME_BORDER_WIDTH
        );

        // bot
        batch.draw(outerBorderTexture,
                Tetris.GAME_BORDER_WIDTH_2,
                0,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH - Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2
        );
    }

    private void drawInnerBorders(Batch batch) {
        // left
        batch.draw(innerBorderTexture,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getHeight() - Tetris.GAME_BORDER_WIDTH
        );

        //top
        batch.draw(innerBorderTexture,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getHeight() - Tetris.GAME_BORDER_WIDTH,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH - Tetris.GAME_BORDER_WIDTH,
                Tetris.GAME_BORDER_WIDTH_2
        );

        //right
        batch.draw(innerBorderTexture,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH - Tetris.GAME_BORDER_WIDTH,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getHeight() - Tetris.GAME_BORDER_WIDTH
        );

        // bot
        batch.draw(innerBorderTexture,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.GAME_BORDER_WIDTH_2,
                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH - Tetris.GAME_BORDER_WIDTH,
                Tetris.GAME_BORDER_WIDTH_2
        );
    }
}
