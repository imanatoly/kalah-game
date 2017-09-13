package net.kalah.game.rule;

import net.kalah.game.Board;
import net.kalah.game.Game;
import net.kalah.game.Slot;
import net.kalah.game.Player;

public class MoveEndInKalah extends Rule {

    @Override
    public void apply(Game game, Slot slot) {

        Player player = slot.getPlayer();
        Board board = game.getBoard();

        byte startCell = board.translateToCell(slot);
        byte stepCount = board.getStoneCountOnCell(startCell);
        byte lastCell = board.calculateLastCell(player, startCell, stepCount);

        boolean onKalah = board.isCellKalah(lastCell);

        if (!onKalah){
            game.switchTurn();
        }
    }
}
