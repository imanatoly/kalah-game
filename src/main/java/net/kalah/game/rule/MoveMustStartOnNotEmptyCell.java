package net.kalah.game.rule;

import net.kalah.game.Game;
import net.kalah.game.Slot;


public class MoveMustStartOnNotEmptyCell extends Rule {

    @Override
    void apply(Game game, Slot slot) {
        if (game.getBoard().getStoneCount(slot) == 0)
            throw new IllegalArgumentException("You cant play empty cell");
    }
}
