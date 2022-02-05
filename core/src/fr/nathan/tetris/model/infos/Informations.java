package fr.nathan.tetris.model.infos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import fr.nathan.tetris.Tetris;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Informations {

    private final BitmapFont font;
    private final long startingTime;
    private final SimpleDateFormat dateFormat;

    private final Texture backgroundTexture;

    private long currentTimeMillis;

    public Informations() {
        this.font = new BitmapFont();
        font.getData().setScale(2);
        this.startingTime = System.currentTimeMillis();
        this.dateFormat   = new SimpleDateFormat("mm:ss", Locale.FRANCE);


        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(0.18f, 0.18f, 0.11f, 1));
        pixmap.fill();

        backgroundTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void update() {
        currentTimeMillis = System.currentTimeMillis();
    }

    public void draw(Batch batch) {

//        batch.draw(backgroundTexture,
//                Tetris.client.getWidth() - Tetris.INFORMATION_AREA_WIDTH,
//                0,
//                Tetris.INFORMATION_AREA_WIDTH,
//                Tetris.client.getHeight());
        drawTimer(batch);
    }
    private void drawTimer(Batch batch) {
        font.draw(batch,
                dateFormat.format(new Date(currentTimeMillis - startingTime)),
                Tetris.informationAreaStart() + Tetris.INFORMATION_AREA_WIDTH / 4,
                -10
        );
    }
}
