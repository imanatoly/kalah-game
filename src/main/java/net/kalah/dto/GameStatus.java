package net.kalah.dto;

import lombok.Builder;

@Builder
public class GameStatus {

    private String playerId;
    private BoardView board;
    private byte score;
    private byte opponentScore;
    private boolean yourTurn;
    private boolean gameEnded;

}
