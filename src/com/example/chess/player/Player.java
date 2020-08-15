package com.example.chess.player;

import com.example.chess.figures.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {

    private final Team team;
    private final CopyOnWriteArrayList<Figure> figures;
    private Player opponent;

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

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
