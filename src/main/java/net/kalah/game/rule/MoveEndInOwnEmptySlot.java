package net.kalah.game.rule;


import net.kalah.game.Game;
import net.kalah.game.Player;
import net.kalah.game.board.Board;
import net.kalah.game.board.Slot;

import static net.kalah.game.Player.A;
import static net.kalah.game.board.Slot.KALAH_A;
import static net.kalah.game.board.Slot.KALAH_B;

public class MoveEndInOwnEmptySlot extends Rule {

    // todo does not work because slot is already emptied
    @Override
    public void apply(Game game, Slot slot, byte stoneCount) {
        Board board = game.getBoard();
        Player player = slot.getPlayer();
        Slot last = board.calculateLast(slot, stoneCount);
        if (last != KALAH_A && last != KALAH_B && last.getPlayer() == player && board.getStoneCount(last) == 1) {
            Slot opposite = board.opposite(last);
            if (player == A) {
                board.addStone(KALAH_A, board.getStoneCount(opposite));
                board.addStone(KALAH_A);
            } else {
                board.addStone(KALAH_B, board.getStoneCount(opposite));
                board.addStone(KALAH_B);
            }
            board.emptySlot(opposite);
            board.emptySlot(last);
        }

    }
}
