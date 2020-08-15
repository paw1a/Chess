package com.example.chess;

import com.example.chess.figures.*;
import com.example.chess.player.Player;
import com.example.chess.player.Team;

import java.awt.*;

public class Board {

    private final Cell[][] cells;

    private final Player player;
    private final Player opponent;
    private GameManager manager;

    public Board(Player player, Player opponent, GameManager manager) {
        this.player = player;
        this.opponent = opponent;
        this.manager = manager;
        cells = new Cell[8][8];
        initBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j].getFigure() != null) {
                    if(cells[i][j].getFigure().getTeam() == player.getTeam())
                        player.getFigures().add(cells[i][j].getFigure());
                    else opponent.getFigures().add(cells[i][j].getFigure());
                }
            }
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j].getFigure() != null) {
                    Figure figure = cells[i][j].getFigure();
                    g.drawImage(figure.getImage(),
                            figure.getX()*100+50, figure.getY()*100+50,100,100, null);
                }
            }
        }
    }

    private void initBoard() {
        for (int i = 0; i < 8; i++) {
            cells[1][i] = new Cell(i, 1, new Pawn(opponent.getTeam(), cells, false));
            cells[6][i] = new Cell(i, 6, new Pawn(player.getTeam(), cells, true));
        }
        for (int i = 0; i < 2; i++) {
            Team team = i == 0 ? opponent.getTeam() : player.getTeam();
            int y = i == 0 ? 0 : 7;
            cells[y][0] = new Cell(0, y, new Rook(team, cells));
            cells[y][1] = new Cell(1, y, new Knight(team, cells));
            cells[y][2] = new Cell(2, y, new Bishop(team, cells));
            cells[y][3] = new Cell(3, y, new Queen(team, cells));
            cells[y][4] = new Cell(4, y, new King(team, cells));
            cells[y][5] = new Cell(5, y, new Bishop(team, cells));
            cells[y][6] = new Cell(6, y, new Knight(team, cells));
            cells[y][7] = new Cell(7, y, new Rook(team, cells));
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i+2][j] = new Cell(j, i+2, null);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}