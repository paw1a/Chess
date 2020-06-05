package com.example.chess;

import com.example.chess.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Team team;
    private List<Figure> figures;
    private Player opponent;

    private boolean isCheck;

    public Player(Team team) {
        this.team = team;
        figures = new ArrayList<>();
        isCheck = false;
    }

    public boolean update() {
        return true;
    }

    private boolean isCheck() {
        return isCheck;
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
