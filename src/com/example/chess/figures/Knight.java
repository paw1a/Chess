package com.example.chess.figures;

import com.example.chess.Cell;
import com.example.chess.player.Team;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure {

    public Knight(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(399, 0, 133, 133);
        else image = tileset.getSubimage(399, 133, 133, 133);
    }

    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        //Буква Г вправо
        if(y-2 >= 0 && x+1 < 8 && (cells[y-2][x+1].getFigure() == null
                || cells[y-2][x+1].getFigure().getTeam() != team)) list.add(cells[y-2][x+1]);
        if(y+1 < 8 && x+2 < 8 && (cells[y+1][x+2].getFigure() == null
                || cells[y+1][x+2].getFigure().getTeam() != team)) list.add(cells[y+1][x+2]);
        if(y+2 < 8 && x-1 >= 0 && (cells[y+2][x-1].getFigure() == null
                || cells[y+2][x-1].getFigure().getTeam() != team)) list.add(cells[y+2][x-1]);
        if(y-1 >= 0 && x-2 >= 0 && (cells[y-1][x-2].getFigure() == null
                || cells[y-1][x-2].getFigure().getTeam() != team)) list.add(cells[y-1][x-2]);
        //Буква Г развернутая влево
        if(y-2 >= 0 && x-1 >= 0 && (cells[y-2][x-1].getFigure() == null
                || cells[y-2][x-1].getFigure().getTeam() != team)) list.add(cells[y-2][x-1]);
        if(y-1 >= 0 && x+2 < 8 && (cells[y-1][x+2].getFigure() == null
                || cells[y-1][x+2].getFigure().getTeam() != team)) list.add(cells[y-1][x+2]);
        if(y+2 < 8 && x+1 < 8 && (cells[y+2][x+1].getFigure() == null
                || cells[y+2][x+1].getFigure().getTeam() != team)) list.add(cells[y+2][x+1]);
        if(y+1 < 8 && x-2 >= 0 && (cells[y+1][x-2].getFigure() == null
                || cells[y+1][x-2].getFigure().getTeam() != team)) list.add(cells[y+1][x-2]);
        return list;
    }
}
