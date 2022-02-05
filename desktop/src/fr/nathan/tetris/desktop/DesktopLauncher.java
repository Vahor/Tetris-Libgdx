package fr.nathan.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.nathan.tetris.Client;

public class DesktopLauncher {

    public static void main(String[] arg) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title     = "Tetris";
        config.resizable = false;

		config.height = 550;
		config.width = 510;

		// Debug

        Client client = new Client();


        new LwjglApplication(client, config);
    }
}
