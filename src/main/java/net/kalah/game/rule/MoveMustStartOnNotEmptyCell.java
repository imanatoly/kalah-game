package net.kalah.game.rule;

import net.kalah.game.Game;
import net.kalah.game.board.Slot;


public class MoveMustStartOnNotEmptyCell extends Rule {

    @Override
    void apply(Game game, Slot slot, byte stoneCount) {
        if (game.getBoard().getStoneCount(slot) == 0)
            throw new IllegalArgumentException("You cant play empty cell");
    }
}
