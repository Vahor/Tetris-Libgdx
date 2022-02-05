package fr.nathan.tetris.updater;

public abstract class TickUpdater {

    private final float delay;
    private long previousRenderTime;

    public TickUpdater(float delay) {
        this.delay = delay;
    }

    public void tick(float delta) {
        long currentTime = System.currentTimeMillis();
        float frameTimeSeconds = (currentTime - previousRenderTime) / 1000f;
        if (frameTimeSeconds >= delay) {
            forceUpdate();
        }
    }
    public void forceUpdate() {
        previousRenderTime = System.currentTimeMillis();
        onUpdate();
    }

    abstract protected void onUpdate();
}
