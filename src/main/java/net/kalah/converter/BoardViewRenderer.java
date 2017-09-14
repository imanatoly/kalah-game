package net.kalah.converter;

import net.kalah.dto.BoardDto;
import net.kalah.game.Player;
import net.kalah.game.board.Board;

import static net.kalah.game.Player.A;
import static net.kalah.game.board.Slot.*;

public class BoardViewRenderer {

    public BoardDto render(Board board, Player player) {

        int[] playerACells = new int[]{
                board.getStoneCount(A_0),
                board.getStoneCount(A_1),
                board.getStoneCount(A_2),
                board.getStoneCount(A_3),
                board.getStoneCount(A_4),
                board.getStoneCount(A_5)};

        int[] playerBCells = new int[]{
                board.getStoneCount(B_0),
                board.getStoneCount(B_1),
                board.getStoneCount(B_2),
                board.getStoneCount(B_3),
                board.getStoneCount(B_4),
                board.getStoneCount(B_5)};

        return BoardDto.builder()
                .opponentKalah(board.getStoneCount((player.opponent() == A) ? KALAH_A : KALAH_B))
                .opponentCells(player.opponent() == A ? playerACells : playerBCells)
                .ownCells(player == A ? playerACells : playerBCells)
                .ownKalah(board.getStoneCount((player == A) ? KALAH_A : KALAH_B))
                .build();
    }
}
