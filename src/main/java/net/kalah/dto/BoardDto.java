package net.kalah.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDto {

    private int opponentKalah;
    private int[] opponentCells;
    private int[] ownCells;
    private int ownKalah;

}
