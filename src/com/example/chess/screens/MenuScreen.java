package com.example.chess.screens;

import com.example.chess.Cell;
import com.example.chess.figures.King;
import com.example.chess.figures.Pawn;
import com.example.chess.figures.Queen;
import com.example.chess.figures.Rook;
import com.example.chess.player.Team;
import org.game.framework.Screen;
import org.game.framework.util.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuScreen implements Screen  {

    private Rectangle singlePlayerRect;
    private Rectangle pcVSpcRect;
    private Rectangle vsYourselfRect;
    private Rectangle exitRect;

    private final Color brownDark = new Color(45, 20, 6, 255);
    private final Color brownLight = new Color(0x82560C);

    private BufferedImage kingImage;
    private BufferedImage queenImage;

    private int inputDelay;

    @Override
    public void create() {
        singlePlayerRect = new Rectangle(200, 300, 500, 70);
        vsYourselfRect = new Rectangle(200, 450, 500, 70);
        pcVSpcRect = new Rectangle(200, 600, 500, 70);
        exitRect = new Rectangle(300, 750, 300, 70);

        kingImage = new King(Team.WHITE, new Cell[1][1]).getImage();
        queenImage = new Rook(Team.WHITE, new Cell[1][1]).getImage();

        inputDelay = 20;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(brownDark);
        g.fillRect(0, 0, Game.conf.getWidth(), Game.conf.getHeight());

        g.setColor(brownLight.brighter());
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
        g.drawString("ШАХМАТЫ", 270, 80);

        g.drawImage(kingImage, 170, 0, 100, 100, null);
        g.drawImage(kingImage, 640, 0, 100, 100, null);
        g.drawImage(queenImage, 350, 80, 200, 200, null);

        if(inputDelay != 0) {
            inputDelay--;
            return;
        }

        g.setColor(brownLight.darker());
        if(singlePlayerRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) Game.application.setScreen(new GameScreen(1));
            g.fillRect(singlePlayerRect.x-10, singlePlayerRect.y-10, 520, 90);
        } else if(vsYourselfRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) Game.application.setScreen(new GameScreen(2));
            g.fillRect(vsYourselfRect.x-10, vsYourselfRect.y-10, 520, 90);
        } else if(pcVSpcRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) Game.application.setScreen(new GameScreen(3));
            g.fillRect(pcVSpcRect.x-10, pcVSpcRect.y-10, 520, 90);
        } else if(exitRect.contains(Game.input.getMouseX(), Game.input.getMouseY())) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) System.exit(1);
            g.fillRect(exitRect.x-10, exitRect.y-10, 320, 90);
        }

        g.setColor(brownLight);
        g.fill(singlePlayerRect);
        g.fill(vsYourselfRect);
        g.fill(pcVSpcRect);
        g.fill(exitRect);

        g.setColor(Color.WHITE);
        g.drawString("Против ПК", 270, 355);
        g.drawString("Мультиплеер", 240, 505);
        g.drawString("ПК против ПК", 230, 655);
        g.drawString("Выйти", 350, 805);

    }
}
