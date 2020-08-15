package com.example.chess.figures;

import com.example.chess.Cell;
import com.example.chess.player.Team;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure {

    public Queen(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(133, 0, 133, 133);
        else image = tileset.getSubimage(133, 133, 133, 133);
    }

    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        for (int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--) {
            Cell cell = cells[j][i];
            if(addFigure(list, cell)) break;
        }
        for (int i = x+1, j = y-1; i < 8 && j >= 0; i++, j--) {
            Cell cell = cells[j][i];
            if(addFigure(list, cell)) break;
        }
        for (int i = y+1, j = x-1; i < 8 && j >= 0; i++, j--) {
            Cell cell = cells[i][j];
            if(addFigure(list, cell)) break;
        }
        for (int i = y+1, j = x+1; i < 8 && j < 8; i++, j++) {
            Cell cell = cells[i][j];
            if(addFigure(list, cell)) break;
        }
        for (int i = x-1; i >= 0; i--) {
            Cell cell = cells[y][i];
            if(addFigure(list, cell)) break;
        }
        for (int i = x+1; i < 8; i++) {
            Cell cell = cells[y][i];
            if(addFigure(list, cell)) break;
        }
        for (int i = y-1; i >= 0; i--) {
            Cell cell = cells[i][x];
            if(addFigure(list, cell)) break;
        }
        for (int i = y+1; i < 8; i++) {
            Cell cell = cells[i][x];
            if(addFigure(list, cell)) break;
        }
        return list;
    }
}
