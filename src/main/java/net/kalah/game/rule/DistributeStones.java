package net.kalah.game.rule;

import net.kalah.game.Board;
import net.kalah.game.Game;
import net.kalah.game.Slot;

public class DistributeStones extends Rule {

    @Override
    void apply(Game game, Slot slot) {
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
