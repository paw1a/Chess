package com.example.chess;

import com.example.chess.figures.Figure;
import com.example.chess.player.AIController;
import com.example.chess.player.Move;
import com.example.chess.player.Player;
import com.example.chess.player.Team;
import com.example.chess.screens.GameOverScreen;
import org.game.framework.util.Game;

import java.awt.*;
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

    private int gameOverTimer;

    public GameManager(Team playerTeam) {
        player = new Player(playerTeam);
        opponent = new Player(inverseTeam(playerTeam));
        player.setOpponent(opponent);
        opponent.setOpponent(player);

        board = new Board(player, opponent, this);
        cells = board.getCells();

        controller = new MoveController(this);

        activeCell = null;
        currentPlayer = player;

        aiController = new AIController(this, playerTeam);

        gameOverTimer = 0;
    }

    public void update() {
        //Обработка хода активного игрока
        if(currentPlayer.equals(player)) {
            if (activeCell != null && Game.input.isMouseDown(MouseEvent.BUTTON1)) {
                int x = Game.input.getMouseX();
                int y = Game.input.getMouseY();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (cells[i][j].getRect().contains(new Point(x, y))) {
                            List<Cell> list = controller.filterByCheck(
                                    currentPlayer, activeCell.getFigure().getAvailableCells(), activeCell);
                            if (list.contains(cells[i][j])) {
                                controller.move(activeCell, cells[i][j], currentPlayer);
                                currentPlayer = getOpponent(currentPlayer);
                                activeCell = null;
                            }
                        }
                    }
                }
            }
        } else {
            Move move = aiController.getMove(currentPlayer);
            controller.move(move.from, move.to, currentPlayer);
            currentPlayer = getOpponent(currentPlayer);
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
        if(b) gameOverTimer++;
        if(gameOverTimer == 300) Game.application.setScreen(
                new GameOverScreen(currentPlayer.equals(player) ? "2" : "1"));
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        if(controller.isCheck(currentPlayer)) g.drawString("CHECK", 700, 890);

        g.setColor(Color.BLACK);
        String s = currentPlayer.equals(player) ? "YOUR" : "OPPONENT";
        g.drawString("TURN: " + s, 50, 890);

        if(activeCell == null) return;

        g.setColor(new Color(0, 0, 255, 50));
        g.fillRect(activeCell.getX()*100+50, activeCell.getY()*100+50, 100, 100);

        List<Cell> list = controller.filterByCheck(
                currentPlayer, activeCell.getFigure().getAvailableCells(), activeCell);

        g.setColor(Color.GREEN);
        for(Cell cell : list)
            g.fillOval(cell.getX()*100+83, cell.getY()*100+83, 35, 35);
    }

    public static Team inverseTeam(Team team) {
        return team == Team.BLACK ? Team.WHITE : Team.BLACK;
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