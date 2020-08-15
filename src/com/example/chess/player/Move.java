package com.example.chess.player;

import com.example.chess.Cell;

public class Move {

    public Cell from, to;
    public Player player;

    public Move(Cell from, Cell to, Player player) {
        this.from = from;
        this.to = to;
        this.player = player;
    }

    @Override
    public String toString() {
        return String.format("Движение из (%d, %d) в (%d, %d) игроком %d",
                from.getX(), from.getY(), to.getX(), to.getY(), player.getTeam() == Team.WHITE ? 1 : 2);
    }
}
