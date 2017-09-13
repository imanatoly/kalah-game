package net.kalah.game;


import lombok.Getter;

import static java.text.MessageFormat.format;
import static net.kalah.game.Board.PLAYER_A_KALAH;
import static net.kalah.game.Board.PLAYER_B_KALAH;
import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;

@Getter
public class Game {

    private String id;
    private String playerAId;
    private String playerBId;

    private long lastUpdate;

    private Board board;
    private Player turn = A;
    private Player winner = null;

    private byte playerAScore;
    private byte playerBScore;


    public Game(String id, String playerAId, String playerBId, byte initialStoneCount) {
        this.id = id;
        this.playerAId = playerAId;
        this.playerBId = playerBId;
        board = new Board(initialStoneCount);
        lastUpdate = System.currentTimeMillis();
    }

    public void update(){
        playerAScore = board.getStoneCountOnCell(PLAYER_A_KALAH);
        playerBScore = board.getStoneCountOnCell(PLAYER_B_KALAH);
        lastUpdate = System.currentTimeMillis();
    }

    public Player getPlayerById(String playerId){
        if (playerAId.equals(playerId)) {
            return A;
        } else if (playerBId.equals(playerId)) {
            return B;
        } else throw new IllegalArgumentException(format("Player {0} does not belong to this game", playerId));
    }

    public byte getScore(Player player){
        return (player==A) ?  playerAScore : playerBScore;
    }

    public byte getOpponentScore(Player player){
        return (player==A) ? playerBScore : playerAScore;
    }

    public boolean isGameEnded(){
        return winner != null;
    }

    public void switchTurn(){
        turn = (turn==A) ? B : A;
    }

    public void endGame(){
        turn = null;
    }

}
