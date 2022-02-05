package fr.nathan.tetris.model.square;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Square {

    private Texture texture;
    protected int x;
    protected int y;

    /** Constructs a new rectangle with the given corner point in the bottom left and dimensions.
     * @param x The corner point x-coordinate
     * @param y The corner point y-coordinate
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract public void draw(Batch batch);

    public void moveDown() {
        y--;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public boolean isCollidingWith(Square square, int offsetX, int offsetY) {
        return this.x == square.x + offsetX && this.y == square.y + offsetY;
    }

    abstract public boolean canMoveDown();
    abstract public boolean canMoveLeft();
    abstract public boolean canMoveRight();

    public void initializeTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();

        texture = new Texture(pixmap);

        pixmap.dispose();
    }

    public Texture getTexture() {
        return texture;
    }
    public int getY() {return y;}
    public int getX() {return x;}

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
