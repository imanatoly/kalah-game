package net.kalah.converter;

import net.kalah.dto.BoardView;
import net.kalah.game.Board;
import net.kalah.game.Slot;
import net.kalah.game.Player;

import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;
import static net.kalah.game.Slot.A_0;
import static net.kalah.game.Slot.A_1;
import static net.kalah.game.Slot.A_2;
import static net.kalah.game.Slot.A_3;
import static net.kalah.game.Slot.A_4;
import static net.kalah.game.Slot.A_5;
import static net.kalah.game.Slot.B_0;
import static net.kalah.game.Slot.B_1;
import static net.kalah.game.Slot.B_2;
import static net.kalah.game.Slot.B_3;
import static net.kalah.game.Slot.B_4;
import static net.kalah.game.Slot.B_5;

public class BoardViewRenderer {

    public BoardView render(Board board, Player player){

        byte[] playerACells = new byte[]{
                board.getStoneCountOnSlot(A_0),
                board.getStoneCountOnSlot(A_1),
                board.getStoneCountOnSlot(A_2),
                board.getStoneCountOnSlot(A_3),
                board.getStoneCountOnSlot(A_4),
                board.getStoneCountOnSlot(A_5)};

        byte[] playerBCells = new byte[]{
                board.getStoneCountOnSlot(B_0),
                board.getStoneCountOnSlot(B_1),
                board.getStoneCountOnSlot(B_2),
                board.getStoneCountOnSlot(B_3),
                board.getStoneCountOnSlot(B_4),
                board.getStoneCountOnSlot(B_5)};

        return BoardView.builder()
                .opponentKalah(board.getStoneCountOnOpponentsKalah(player))
                .opponentCells(player.opponent()==A ? playerBCells : playerACells)
                .ownCells(player==A ? playerACells : playerBCells)
                .ownKalah(board.getStoneCountOnKalah(player))
                .build();
    }
}
