package net.kalah.game.rule;

import net.kalah.game.Game;
import net.kalah.game.board.Slot;

public abstract class Rule {

    private Rule next;

    abstract void apply(Game game, Slot slot, byte stoneCountInSlot);

    public Rule setNextRule(Rule next) {
        this.next = next;
        return next;
    }

    public void execute(Game game, Slot slot, byte stoneCountInSlot) {
        apply(game, slot, stoneCountInSlot);
        if (next != null) {
            next.execute(game, slot, stoneCountInSlot);
        }
    }

}
