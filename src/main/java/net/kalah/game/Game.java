package net.kalah.game;


import lombok.Getter;
import net.kalah.game.board.Board;
import net.kalah.util.IdUtil;

import static java.text.MessageFormat.format;
import static net.kalah.game.GameStatus.*;
import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;
import static net.kalah.game.board.Slot.KALAH_A;
import static net.kalah.game.board.Slot.KALAH_B;

@Getter
public class Game {

    private String id;
    private String playerAId;
    private String playerBId;

    private long lastUpdate;

    private Board board;
    private GameStatus status = GameStatus.CREATED;
    private Player turn = A;
    private Player winner = null;

    private byte playerAScore;
    private byte playerBScore;


    public Game(byte initialStoneCount) {
        this.id = IdUtil.createId();
        this.playerAId = IdUtil.createId();
        this.playerBId = IdUtil.createId();
        board = new Board(initialStoneCount);
        lastUpdate = System.currentTimeMillis();
    }

    void update() {
        playerAScore = board.getStoneCount(KALAH_A);
        playerBScore = board.getStoneCount(KALAH_B);
        lastUpdate = System.currentTimeMillis();
    }

    public Player getPlayerById(String playerId) {
        if (playerAId.equals(playerId)) {
            return A;
        } else if (playerBId.equals(playerId)) {
            return B;
        } else throw new IllegalArgumentException(format("Player {0} does not belong to this game", playerId));
    }

    public byte getScore(Player player) {
        return (player == A) ? playerAScore : playerBScore;
    }

    public boolean isGameEnded() {
        return winner != null;
    }

    public void switchTurn() {
        turn = (turn == A) ? B : A;
        status = (turn == A) ? TURN_A : TURN_B;
    }

    public void startGame() {
        update();
        status = TURN_A;
    }

    public void endGame() {
        update();
        turn = null;
        status = ENDED;
        winner = (playerAScore > playerBScore) ? A : B;
    }

}
