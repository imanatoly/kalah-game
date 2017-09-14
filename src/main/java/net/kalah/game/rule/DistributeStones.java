package net.kalah.game.rule;

import net.kalah.game.board.Board;
import net.kalah.game.Game;
import net.kalah.game.board.Slot;

public class DistributeStones extends Rule {

    @Override
    void apply(Game game, Slot slot, byte stoneCount) {
        Board board = game.getBoard();

        Slot current = slot;
        int stepCount = board.getStoneCount(current);

        for (int i = 0; i < stepCount; i++) {
            current = board.nextSlot(slot.getPlayer(), current);
            board.addStone(current);
        }

        board.emptySlot(slot);
    }
}
