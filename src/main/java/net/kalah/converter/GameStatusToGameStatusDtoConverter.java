package net.kalah.converter;

import net.kalah.dto.GameStatusDto;
import net.kalah.game.GameStatus;
import net.kalah.game.Player;

import static net.kalah.dto.GameStatusDto.*;
import static net.kalah.game.Player.A;

public class GameStatusToGameStatusDtoConverter {

    public GameStatusDto convert(GameStatus gameStatus, Player player) {

        switch (gameStatus) {
            case CREATED:
            case WAITING_OPPONENT:
                return WAITING_OPPONENT;
            case TURN_A:
                return (player == A) ? YOUR_TURN : OPPONENT_TURN;
            case TURN_B:
                return (player == A) ? OPPONENT_TURN : YOUR_TURN;
            default:
                return ENDED;
        }

    }
}
