package net.kalah.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameDto {

    private String playerId;
    private BoardDto board;
    private byte score;
    private byte opponentScore;
    private boolean yourTurn;
    private boolean gameEnded;

}
