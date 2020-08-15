package com.example.chess.player;

import com.example.chess.Cell;
import com.example.chess.GameManager;
import com.example.chess.MoveController;
import com.example.chess.Utils;
import com.example.chess.figures.*;

import java.util.List;
import java.util.Random;

public class AIController {

    private final GameManager manager;
    private final Cell[][] cells;
    private final MoveController moveController;
    private final Team leadPlayerTeam;
    private int movesCount;

    public AIController(GameManager manager, Team leadPlayerTeam) {
        this.manager = manager;
        this.cells = manager.getBoard().getCells();
        this.moveController = manager.getController();
        this.leadPlayerTeam = leadPlayerTeam;
    }

    public Move getMove(Player player) {
        return rootMinimax(4, player);
    }

    private Move rootMinimax(int level, Player player) {
        movesCount = 0;

        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        for(Figure figure : player.getFigures()) {
            for(Cell cell : moveController.filterByCheck(player,
                    figure.getAvailableCells(), moveController.findCellByFigure(figure, cells))) {
                Move move = new Move(moveController.findCellByFigure(figure, cells), cell, player);

                Figure tempFigure = move.to.getFigure();
                int index = manager.getOpponent(player).getFigures().indexOf(tempFigure);

                moveController.move(move.from, move.to, move.player);
                int value = minimax(level-1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, manager.getOpponent(player));
                if(value > bestValue) {
                    bestValue = value;
                    bestMove = move;
                }
                moveController.move(move.to, move.from, move.player);

                if(index != -1) manager.getOpponent(player).getFigures().add(index, tempFigure);
                move.to.setFigure(tempFigure);
            }
        }
        System.out.println(movesCount);
        return bestMove;
    }

    private int minimax(int level, int alpha, int beta, boolean isMaximizer, Player player) {
        if(level == 0) {
            if(isMaximizer) return evaluationFunction(player);
            else return evaluationFunction(manager.getOpponent(player));
        }

        int minmax = isMaximizer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Player opponent = manager.getOpponent(player);

        boolean alphaBetaCut = false;
        for(Figure figure : player.getFigures()) {
            //Отсечение
            if(alphaBetaCut) break;

            for(Cell cell : moveController.filterByCheck(player,
                    figure.getAvailableCells(), moveController.findCellByFigure(figure, cells))) {
                Move move = new Move(moveController.findCellByFigure(figure, cells), cell, player);

                //Запоминаем фигуру, если она будет съедена
                Figure tempFigure = move.to.getFigure();
                int index = opponent.getFigures().indexOf(tempFigure);

                moveController.move(move.from, move.to, move.player);
                if(isMaximizer) {
                    minmax = Math.max(minimax(level-1, alpha, beta, false, opponent), minmax);
                    alpha = Math.max(alpha, minmax);
                } else {
                    minmax = Math.min(minimax(level-1, alpha, beta, true, opponent), minmax);
                    beta = Math.min(beta, minmax);
                }
                if(beta <= alpha) alphaBetaCut = true;
                moveController.move(move.to, move.from, move.player);

                //озвращаем съеденную фигуру
                if(index != -1) opponent.getFigures().add(index, tempFigure);
                move.to.setFigure(tempFigure);
            }
        }
        return minmax;
    }

    public int evaluationFunction(Player player) {
        movesCount++;
        int sum = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Cell cell = cells[i][j];
                if(cell.getFigure() != null) {
                    Figure figure = cell.getFigure();
                    if(figure instanceof Pawn)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.PAWN : -Utils.PAWN) +
                                (figure.getTeam() == Team.WHITE ? -Utils.PAWN_BONUS[i][j] : Utils.PAWN_BONUS[7 - i][7 - j]);
                    else if(figure instanceof Knight)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.KNIGHT : -Utils.KNIGHT) +
                                (figure.getTeam() == Team.WHITE ? -Utils.KNIGHT_BONUS[i][j] : Utils.KNIGHT_BONUS[7-i][7-j]);
                    else if(figure instanceof Bishop)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.BISHOP : -Utils.BISHOP) +
                                (figure.getTeam() == Team.WHITE ? -Utils.BISHOP_BONUS[i][j] : Utils.BISHOP_BONUS[7-i][7-j]);
                    else if(figure instanceof Rook)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.ROOK : -Utils.ROOK) +
                                (figure.getTeam() == Team.WHITE ? -Utils.ROOK_BONUS[i][j] : Utils.ROOK_BONUS[7-i][7-j]);
                    else if(figure instanceof Queen)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.QUEEN : -Utils.QUEEN) +
                                (figure.getTeam() == Team.WHITE ? -Utils.QUEEN_BONUS[i][j] : Utils.QUEEN_BONUS[7-i][7-j]);
                    else if(figure instanceof King)
                        sum += (player.getTeam() == figure.getTeam() ? Utils.KING : -Utils.KING) +
                                (figure.getTeam() == Team.WHITE ? -Utils.KING_BONUS[i][j] : Utils.KING_BONUS[7-i][7-j]);
                }
            }
        }
        if(moveController.isCheck(manager.getOpponent(player))) sum += 20000;
        return sum;
    }

    private Move getRandomMove(Player player) {
        Random random = new Random();
        Figure figure;
        List<Cell> list;
        do {
            figure = player.getFigures().get(random.nextInt(player.getFigures().size()));
            list = moveController.filterByCheck(player,
                    figure.getAvailableCells(), moveController.findCellByFigure(figure, cells));
        } while (list.isEmpty());

        Cell cell = list.get(random.nextInt(list.size()));

        return new Move(moveController.findCellByFigure(figure, cells), cell, player);
    }

}
