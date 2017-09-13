package net.kalah.game.rule;

import net.kalah.game.Board;
import net.kalah.game.Game;
import net.kalah.game.Slot;


public class MoveMustStartOnNotEmptyCell extends Rule {

    @Override
    void apply(Game game, Slot slot) {
        Board board = game.getBoard();
        byte currentCell = board.translateToCell(slot);
        if (board.getStoneCountOnCell(currentCell)==0)
            throw new IllegalArgumentException("You cant play empty cell");
    }
}
