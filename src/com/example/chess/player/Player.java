package com.example.chess.player;

import com.example.chess.Cell;
import com.example.chess.figures.Figure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {

    private final Team team;
    private final CopyOnWriteArrayList<Figure> figures;

    public Cell lastMoveFrom;
    public Cell lastMoveTo;

    public Player(Team team) {
        this.team = team;
        figures = new CopyOnWriteArrayList<>();
    }

    public Team getTeam() {
        return team;
    }

    public List<Figure> getFigures() {
        return figures;
    }
}
