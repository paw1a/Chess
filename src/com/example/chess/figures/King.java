package com.example.chess.figures;

import com.example.chess.Cell;
import com.example.chess.player.Team;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {

    public King(Team team, Cell[][] cells) {
        super(team, cells);
        if(team == Team.WHITE) image = tileset.getSubimage(0, 0, 133, 133);
        else image = tileset.getSubimage(0, 133, 133, 133);
    }

    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        if(y > 0 && x < 7 && (cells[y-1][x+1].getFigure() == null
                || cells[y-1][x+1].getFigure().getTeam() != team)) list.add(cells[y-1][x+1]);
        if(x < 7 && (cells[y][x+1].getFigure() == null
                || cells[y][x+1].getFigure().getTeam() != team)) list.add(cells[y][x+1]);
        if(y < 7 && x < 7 && (cells[y+1][x+1].getFigure() == null
                || cells[y+1][x+1].getFigure().getTeam() != team)) list.add(cells[y+1][x+1]);
        if(y < 7 && (cells[y+1][x].getFigure() == null
                || cells[y+1][x].getFigure().getTeam() != team)) list.add(cells[y+1][x]);
        if(y < 7 && x > 0 && (cells[y+1][x-1].getFigure() == null
                || cells[y+1][x-1].getFigure().getTeam() != team)) list.add(cells[y+1][x-1]);
        if(x > 0 && (cells[y][x-1].getFigure() == null
                || cells[y][x-1].getFigure().getTeam() != team)) list.add(cells[y][x-1]);
        if(y > 0 && x > 0 && (cells[y-1][x-1].getFigure() == null
                || cells[y-1][x-1].getFigure().getTeam() != team)) list.add(cells[y-1][x-1]);
        if(y > 0 && (cells[y-1][x].getFigure() == null
                || cells[y-1][x].getFigure().getTeam() != team)) list.add(cells[y-1][x]);
        return list;
    }
}
