package fr.nathan.tetris;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.nathan.tetris.controller.SquareController;
import fr.nathan.tetris.screen.GameScreen;

import java.util.Random;

public class Client extends Game {

    private static final String TAG = "Client";

    private float width, height;

    private SpriteBatch batch;

    public Client() {
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void setScreen(Screen screen) {
        Gdx.app.debug(TAG, "Screen changed to : " + screen.getClass().getSimpleName());
        super.setScreen(screen);
    }

    @Override
    public void create() {
        Tetris.client = this;
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Tetris.batch  = batch = new SpriteBatch();
        Tetris.random = new Random(5000);


        Tetris.squareController = new SquareController();

        setScreen(new GameScreen());

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.width  = width;
        this.height = height;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }

    @Override
    public void dispose() {
        Gdx.app.debug(TAG, "Disposing screen...");
        super.dispose();

        Gdx.app.debug(TAG, "Disposing batch...");
        batch.dispose();
    }
}
