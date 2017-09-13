package net.kalah.game;

import net.kalah.game.rule.*;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

    private Rule chain;

    public GameEngine() {
        chain = new MoveMustStartOnNotEmptyCell();

        chain.setNextRule(new MoveEndInKalah())
                .setNextRule(new DistributeStones())
                .setNextRule(new MoveEndInOwnEmptySlot())
                .setNextRule(new EndGame());
    }

    public void apply(Game game, Slot slot) {
        if (game.getTurn() == slot.getPlayer()) {
            chain.execute(game, slot);
            game.update();
        } else {
            throw new IllegalArgumentException("Not your turn");
        }
    }

}
