package com.example.chess.screens;

import org.game.framework.Screen;
import org.game.framework.util.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameOverScreen implements Screen {

    private long timer;
    private String winnerName;

    public GameOverScreen(String winner) {
        winnerName = winner;
    }

    @Override
    public void create() {
        timer = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if(System.currentTimeMillis() - timer > 2000 && Game.input.isMouseDown(MouseEvent.BUTTON1))
            Game.application.setScreen(new GameScreen());
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.conf.getWidth(), Game.conf.getHeight());

        g.setColor(Color.WHITE);
        g.drawString("Player " + winnerName + " WIN!", 300, 300);
        g.drawString("CLICK TO PLAY AGAIN", 200, 400);
    }
}
