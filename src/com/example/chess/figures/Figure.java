package com.example.chess.figures;

import com.example.chess.Cell;
import com.example.chess.Team;
import org.game.framework.util.Game;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class Figure {
    protected int x, y;

    protected BufferedImage image;
    protected Team team;
    protected Cell[][] cells;

    protected static BufferedImage tileset = Game.files.loadImage("/tileset4.png");

    public Figure(Team team, Cell[][] cells) {
        this.team = team;
        this.cells = cells;
    }

    public abstract List<Cell> getAvailableCells();

    protected boolean addFigure(List<Cell> list, Cell cell) {
        if(cell.getFigure() == null) list.add(cell);
        else {
            if(cell.getFigure().getTeam() == team) return true;
            else {
                list.add(cell);
                return true;
            }
        }
        return false;
    }

    public BufferedImage getImage() { return image; }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Team getTeam() {
        return team;
    }
}