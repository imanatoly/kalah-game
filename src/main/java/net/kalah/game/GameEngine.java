package net.kalah.game;

import net.kalah.game.rule.*;


public class GameEngine {

    private Rule chain;

    public GameEngine() {
        chain = new MoveMustStartOnNotEmptyCell();

        chain.setNextRule(new MoveEndInOwnEmptySlot())
                .setNextRule(new MoveEndInKalah())
                .setNextRule(new DistributeStones())
                .setNextRule(new EndGame());
    }

    public void apply(Game game, Slot slot){
        chain.execute(game, slot);
        game.update();
    }

}
