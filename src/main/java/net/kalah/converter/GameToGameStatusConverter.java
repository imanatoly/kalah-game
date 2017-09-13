package net.kalah.converter;

import net.kalah.dto.BoardView;
import net.kalah.dto.GameStatus;
import net.kalah.game.Game;
import net.kalah.game.Player;
import org.springframework.stereotype.Component;

@Component
public class GameToGameStatusConverter {

    private BoardViewRenderer boardViewRenderer = new BoardViewRenderer();

    public GameStatus convert(Game game, String playerId) {

        Player player = game.getPlayerById(playerId);
        BoardView boardView = boardViewRenderer.render(game.getBoard(), player);

        return GameStatus.builder()
                .board(boardView)
                .gameEnded(game.isGameEnded())
                .playerId(playerId)
                .score(game.getScore(player))
                .opponentScore(game.getOpponentScore(player))
                .yourTurn(game.getTurn()==player)
                .gameEnded(game.isGameEnded())
                .build();
    }
}
