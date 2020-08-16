package com.example.chess;

import com.example.chess.figures.Figure;
import com.example.chess.player.AIController;
import com.example.chess.player.Move;
import com.example.chess.player.Player;
import com.example.chess.player.Team;
import com.example.chess.screens.MenuScreen;
import org.game.framework.util.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameManager {

    private final Board board;
    public Cell activeCell;
    private final Cell[][] cells;
    private final MoveController controller;
    private final AIController aiController;

    public Player currentPlayer;
    private final Player player;
    private final Player opponent;

    private Player winner = null;

    private int gameOverTimer;
    private int playerChangeTimer;
    private final int gameMode;
    private int movesCount;

    public GameManager(int gameMode) {
        this.gameMode = gameMode;

        player = new Player(Team.WHITE);
        opponent = new Player(Team.BLACK);

        board = new Board(player, opponent, this);
        cells = board.getCells();

        controller = new MoveController(this);

        activeCell = null;
        currentPlayer = player;

        aiController = new AIController(this);

        gameOverTimer = -1;
        playerChangeTimer = 0;
        movesCount = 0;
    }

    public void update() {
        if(Game.input.isKeyDown(KeyEvent.VK_ESCAPE))
            Game.application.setScreen(new MenuScreen());
        if(playerChangeTimer != 0) {
            playerChangeTimer--;
            return;
        }

        if(gameOverTimer > 0) {
            gameOverTimer--;
            return;
        }

        if(gameOverTimer == 0) {
            if (Game.input.isMouseDown(MouseEvent.BUTTON1)) Game.application.setScreen(new MenuScreen());
            return;
        }


        //Обработка ходов в зависимости от режима игры
        if(gameMode == Utils.SINGLE_PLAYER_MODE) {
            if (currentPlayer.equals(player)) {
                inputUpdate();
            } else {
                Move move = aiController.getMove(currentPlayer);

                if(move != null) {
                    controller.move(move.from, move.to, currentPlayer);
                    currentPlayer.lastMoveFrom = move.from;
                    currentPlayer.lastMoveTo = move.to;
                } else winner = getOpponent(currentPlayer);

                currentPlayer = getOpponent(currentPlayer);
                playerChangeTimer = 10;
            }
        } else if(gameMode == Utils.MULTIPLAYER_MODE) {
            inputUpdate();
        } else {
            Move move;
            if(movesCount % 10 == 0) move = aiController.getRandomMove(currentPlayer);
            else move = aiController.getMove(currentPlayer);

            if(move != null) {
                controller.move(move.from, move.to, currentPlayer);
                currentPlayer.lastMoveFrom = move.from;
                currentPlayer.lastMoveTo = move.to;
            }
            else winner = getOpponent(currentPlayer);

            currentPlayer = getOpponent(currentPlayer);
            playerChangeTimer = 100;
            movesCount++;
        }

        //Проверка на шах
        List<Figure> list = currentPlayer.getFigures();
        boolean b = true;
        for (int i = 0; i < list.size(); i++) {
            Figure fig = list.get(i);
            if(!controller.filterByCheck(currentPlayer, fig.getAvailableCells(),
                    controller.findCellByFigure(fig, cells)).isEmpty()) {
                b = false;
                break;
            }
        }
        if(b) winner = getOpponent(currentPlayer);

        if(winner != null && gameOverTimer == -1) gameOverTimer = 200;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        if(controller.isCheck(currentPlayer) && winner == null) g.drawString("CHECK", 700, 890);
        if(winner != null) g.drawString("ИГРОК " + (winner.equals(player) ? "1" : "2") + " ПОБЕДИЛ", 500, 890);

        g.setColor(Color.BLACK);
        String s = currentPlayer.equals(player) ? "YOUR" : "OPPONENT";
        if(gameOverTimer != 0) g.drawString("TURN: " + s, 50, 890);
        else g.drawString("Нажмите для выхода", 50, 890);

        if(getOpponent(currentPlayer).lastMoveTo != null) {
            g.setColor(new Color(0x66991702, true));
            g.fill(getOpponent(currentPlayer).lastMoveFrom.getRect());
            g.fill(getOpponent(currentPlayer).lastMoveTo.getRect());
        }

        if(activeCell == null) return;

        g.setColor(new Color(0, 0, 255, 50));
        g.fillRect(activeCell.getX()*100+50, activeCell.getY()*100+50, 100, 100);

        List<Cell> list = controller.filterByCheck(
                currentPlayer, activeCell.getFigure().getAvailableCells(), activeCell);

        g.setColor(Color.GREEN);
        for(Cell cell : list)
            g.fillOval(cell.getX()*100+83, cell.getY()*100+83, 35, 35);
    }

    private void inputUpdate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j].getRect().contains(Game.input.getMouseX(), Game.input.getMouseY())
                        && Game.input.isMouseDown(MouseEvent.BUTTON1))
                    if(currentPlayer.getFigures().contains(
                            cells[i][j].getFigure())) activeCell = board.getCells()[i][j];
            }
        }

        if (activeCell != null && Game.input.isMouseDown(MouseEvent.BUTTON1)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (cells[i][j].getRect().contains(Game.input.getMouseX(), Game.input.getMouseY())) {
                        List<Cell> list = controller.filterByCheck(
                                currentPlayer, activeCell.getFigure().getAvailableCells(), activeCell);
                        if (list.contains(cells[i][j])) {
                            controller.move(activeCell, cells[i][j], currentPlayer);

                            currentPlayer.lastMoveFrom = activeCell;
                            currentPlayer.lastMoveTo = cells[i][j];

                            currentPlayer = getOpponent(currentPlayer);
                            playerChangeTimer = 20;
                            activeCell = null;
                        }
                    }
                }
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public MoveController getController() {
        return controller;
    }

    public Player getOpponent(Player player) {
        if(player == this.player) return opponent;
        else return this.player;
    }
}