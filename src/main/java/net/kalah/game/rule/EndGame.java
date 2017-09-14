package net.kalah.game.rule;

import net.kalah.game.Game;
import net.kalah.game.board.Board;
import net.kalah.game.board.Slot;

import static net.kalah.game.board.Slot.*;

public class EndGame extends Rule {

    @Override
    public void apply(Game game, Slot slot, byte stoneCount) {
        Board board = game.getBoard();

        byte totalA = (byte) (board.getStoneCount(A_0)
                + board.getStoneCount(A_1)
                + board.getStoneCount(A_2)
                + board.getStoneCount(A_3)
                + board.getStoneCount(A_4)
                + board.getStoneCount(A_5));

        byte totalB = (byte) (board.getStoneCount(B_0)
                + board.getStoneCount(B_1)
                + board.getStoneCount(B_2)
                + board.getStoneCount(B_3)
                + board.getStoneCount(B_4)
                + board.getStoneCount(B_5));

        if (totalA == 0 || totalB == 0) {
            board.addStone(KALAH_A, totalA);
            board.addStone(KALAH_B, totalB);
            board.emptySlots(A_0, A_1, A_2, A_3, A_4, A_5, B_0, B_1, B_2, B_3, B_4, B_5);
            game.endGame();
        }
    }
}
