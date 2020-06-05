package com.example.chess;

import com.example.chess.figures.Figure;

import java.awt.*;

public class Cell {
    private int x, y;
    private Figure figure;
    private Rectangle rect;

    public Cell(int x, int y, Figure figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
        if(figure != null) {
            figure.setX(x);
            figure.setY(y);
        }
        rect = new Rectangle(x*100+50, y*100+50, 100, 100);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Rectangle getRect() {
        return rect;
    }
}
