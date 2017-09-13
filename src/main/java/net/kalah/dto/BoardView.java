package net.kalah.dto;


import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class BoardView {

    private byte opponentKalah;
    private byte[] opponentCells;
    private byte[] ownCells;
    private byte ownKalah;

}
