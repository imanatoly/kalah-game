package net.kalah.game.rule;

import net.kalah.game.Game;
import net.kalah.game.Slot;

public abstract class Rule {

    private Rule next;

    abstract void apply(Game game, Slot slot);

    public Rule setNextRule(Rule next) {
        this.next = next;
        return next;
    }

    public void execute(Game game, Slot slot) {
        apply(game, slot);
        if (next != null) {
            next.execute(game, slot);
        }
    }

}
