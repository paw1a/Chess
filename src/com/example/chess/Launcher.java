package com.example.chess;

import com.example.chess.screens.GameScreen;
import com.example.chess.screens.MenuScreen;
import org.game.framework.Application;
import org.game.framework.util.Configuration;

public class Launcher {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setFPS(60);
        config.setWidth(900);
        config.setHeight(900);
        config.setShowFPS(false);
        config.setTitle("Chess");
        new Application(config).setScreen(new MenuScreen());
    }
}