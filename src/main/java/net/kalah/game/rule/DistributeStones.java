package net.kalah.game.rule;

import net.kalah.game.Board;
import net.kalah.game.Game;
import net.kalah.game.Slot;

public class DistributeStones extends Rule {

    @Override
    void apply(Game game, Slot slot) {
        Board board = game.getBoard();

        byte currentCell = board.translateToCell(slot);
        int stepCount = board.getStoneCountOnCell(currentCell);

        for (int i=0; i<stepCount; i++){
            currentCell = board.nextCell(slot.getPlayer(), currentCell);
            board.addStoneToCell(currentCell);
        }
    }
}
