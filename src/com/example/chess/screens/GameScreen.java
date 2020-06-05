package com.example.chess.screens;

import com.example.chess.Board;
import com.example.chess.GameManager;
import com.example.chess.Team;
import org.game.framework.Screen;
import org.game.framework.util.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameScreen implements Screen {

    private Board board;
    private GameManager manager;

    @Override
    public void create() {
        manager = new GameManager(Team.WHITE);
        board = manager.getBoard();
    }

    @Override
    public void update() {
        //Input handling
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getCells()[i][j].getRect().contains(
                        new Point(Game.input.getMouseX(), Game.input.getMouseY()))
                        && Game.input.isMouseDown(MouseEvent.BUTTON1))
                    if(manager.currentPlayer.getFigures().contains(
                            board.getCells()[i][j].getFigure())) manager.activeCell = board.getCells()[i][j];
            }
        }
        manager.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Game.conf.getWidth(), Game.conf.getHeight());

        //Draw board
        Color brown = Color.decode("#D18B47");
        Color light = Color.decode("#FFCE9E");
        Color color = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i % 2 == 0 && j == 0) color = light;
                else if(i % 2 != 0 && j == 0) color = brown;
                g.setColor(color);
                g.fillRect(j*100 + 50, i*100 + 50, 100, 100);
                if (color.equals(brown)) color = light;
                else color = brown;
            }
        }

        //Draw hover effect
        g.setColor(new Color(0, 0, 0, 60));
        if(Game.input.getMouseX() < 850 && Game.input.getMouseY() < 850)
            g.fillRect((Game.input.getMouseX()-50)/100*100+50,
                    (Game.input.getMouseY()-50)/100*100+50, 100, 100);

        //Draw coordinates
        Font font = g.getFont().deriveFont(Font.BOLD, 50f);
        g.setFont(font);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            g.drawString((char)('A'+i) + "", 85 + i*100, 45);
            g.drawString((i+1)+"", 10, 115 + i*100);
        }

        g.setColor(new Color(0, 0, 255, 50));
        if(manager.activeCell != null)
            g.fillRect(manager.activeCell.getX()*100+50, manager.activeCell.getY()*100+50, 100, 100);

        board.draw(g);
        manager.draw(g);
    }
}