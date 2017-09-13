package net.kalah.game.rule;

import net.kalah.game.Board;
import net.kalah.game.Game;
import net.kalah.game.Slot;

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
import static net.kalah.game.Slot.KALAH_A;
import static net.kalah.game.Slot.KALAH_B;

public class EndGame extends Rule {

    @Override
    public void apply(Game game, Slot slot) {
        Board board = game.getBoard();

        byte totalA = (byte)(board.getStoneCount(A_0)
                + board.getStoneCount(A_1)
                + board.getStoneCount(A_2)
                + board.getStoneCount(A_3)
                + board.getStoneCount(A_4)
                + board.getStoneCount(A_5));

        byte totalB = (byte)(board.getStoneCount(B_0)
                + board.getStoneCount(B_1)
                + board.getStoneCount(B_2)
                + board.getStoneCount(B_3)
                + board.getStoneCount(B_4)
                + board.getStoneCount(B_5));

        if (totalA==0 || totalB==0){
            game.endGame();
            board.addStone(KALAH_A, totalA);
            board.addStone(KALAH_B, totalB);
        }
    }
}
