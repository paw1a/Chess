package com.example.chess.figures;

import com.example.chess.Cell;
import com.example.chess.player.Team;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {
    private boolean goUp;

    public Pawn(Team team, Cell[][] cells, boolean goUp) {
        super(team, cells);
        this.goUp = goUp;
        if(team == Team.WHITE) image = tileset.getSubimage(665, 0, 133, 133);
        else image = tileset.getSubimage(665, 133, 133, 133);
    }

    @Override
    public List<Cell> getAvailableCells() {
        List<Cell> list = new ArrayList<>();
        if(goUp) {
            if(y > 0 && x < 7 && cells[y-1][x+1].getFigure() != null &&
                    cells[y-1][x+1].getFigure().getTeam() != team) list.add(cells[y-1][x+1]);
            if(y > 0 && x > 0 && cells[y-1][x-1].getFigure() != null &&
                    cells[y-1][x-1].getFigure().getTeam() != team) list.add(cells[y-1][x-1]);
            if(y > 0 && cells[y-1][x].getFigure() == null) {
                list.add(cells[y-1][x]);
                if (y == 6 && cells[y - 2][x].getFigure() == null) list.add(cells[y-2][x]);
            }
        } else {
            if (x < 7 && y < 7 && cells[y + 1][x + 1].getFigure() != null &&
                    cells[y + 1][x + 1].getFigure().getTeam() != team) list.add(cells[y + 1][x + 1]);
            if (x > 0 && y < 7 && cells[y + 1][x - 1].getFigure() != null &&
                    cells[y + 1][x - 1].getFigure().getTeam() != team) list.add(cells[y + 1][x - 1]);
            if (y < 7 && cells[y + 1][x].getFigure() == null) {
                list.add(cells[y + 1][x]);
                if (y == 1 && cells[y + 2][x].getFigure() == null) list.add(cells[y + 2][x]);
            }
        }
        return list;
    }
}
