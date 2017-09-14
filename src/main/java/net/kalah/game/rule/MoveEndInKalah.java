package net.kalah.game.rule;

import net.kalah.game.board.Board;
import net.kalah.game.Game;
import net.kalah.game.board.Slot;

import static net.kalah.game.board.Slot.KALAH_A;
import static net.kalah.game.board.Slot.KALAH_B;

public class MoveEndInKalah extends Rule {

    @Override
    public void apply(Game game, Slot slot, byte stoneCount) {

        Board board = game.getBoard();

        Slot last = board.calculateLast(slot, board.getStoneCount(slot));

        if (last != KALAH_A && last != KALAH_B) {
            game.switchTurn();
        }
    }
}
