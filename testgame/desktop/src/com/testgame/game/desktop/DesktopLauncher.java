package com.testgame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testgame.game.TestGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width=600;
		config.height=600;
		config.title="dumbsnakegame";
		config.vSyncEnabled = false; // Setting to false disables vertical sync

		config.foregroundFPS = 60; // Setting to 0 disables foreground fps throttling

		config.backgroundFPS = 60;
		new LwjglApplication(new TestGame(), config);
	}
}
