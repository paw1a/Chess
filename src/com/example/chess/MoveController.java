package com.example.chess;

import com.example.chess.figures.Figure;
import com.example.chess.figures.King;

import java.util.List;

public class MoveController {

    private GameManager manager;

    public MoveController(GameManager manager) {
        this.manager = manager;
    }

    public List<Cell> filterByCheck(Player player, List<Cell> list, Cell from) {
        Figure tempFigure = null;
        for (int i = 0; i < list.size(); i++) {
            Cell cell = list.get(i);
            boolean wasRemoved = false;
            if(cell.getFigure() != null) {
                tempFigure = cell.getFigure();
                manager.getOpponent(player).getFigures().remove(tempFigure);
                wasRemoved = true;
            }
            move(from, cell, player);

            if(isCheck(player)) {
                list.remove(cell);
                i--;
            }
            move(cell, from, player);
            cell.setFigure(tempFigure);
            if(wasRemoved) manager.getOpponent(player).getFigures().add(tempFigure);
            tempFigure = null;
        }

        return list;
    }

    public void move(Cell from, Cell to, Player player) {
        if(from == null || to == null || from.getFigure() == null) return;

        if(from.getFigure().getTeam() == player.getTeam()) {
            if(to.getFigure() != null
                    && to.getFigure().getTeam() == manager.getOpponent(player).getTeam())
                manager.getOpponent(player).getFigures().remove(to.getFigure());
            from.getFigure().setX(to.getX());
            from.getFigure().setY(to.getY());
            to.setFigure(from.getFigure());
            from.setFigure(null);
        }
    }

    public boolean isCheck(Player player) {
        List<Figure> opponentFigures = manager.getOpponent(player).getFigures();
        for (int i = 0; i < opponentFigures.size(); i++) {
            Figure figure = opponentFigures.get(i);
            for(Cell cell : figure.getAvailableCells()) {
                if(cell.getFigure() != null && cell.getFigure().getTeam() == player.getTeam() &&
                    cell.getFigure() instanceof King) return true;
            }
        }
        return false;
    }

    public Cell findCellByFigure(Figure figure, Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if(cells[i][j].getFigure() != null &&
                        cells[i][j].getFigure().equals(figure)) return cells[i][j];
            }
        }
        return null;
    }

}