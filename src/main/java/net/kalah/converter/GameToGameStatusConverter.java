package net.kalah.converter;

import net.kalah.dto.BoardDto;
import net.kalah.dto.GameDto;
import net.kalah.game.Game;
import net.kalah.game.Player;
import org.springframework.stereotype.Component;

@Component
public class GameToGameStatusConverter {

    private BoardViewRenderer boardViewRenderer = new BoardViewRenderer();

    public GameDto convert(Game game, String playerId) {

        Player player = game.getPlayerById(playerId);
        BoardDto boardView = boardViewRenderer.render(game.getBoard(), player);

        return GameDto.builder()
                .board(boardView)
                .gameEnded(game.isGameEnded())
                .playerId(playerId)
                .score(game.getScore(player))
                .opponentScore(game.getScore(player.opponent()))
                .yourTurn(game.getTurn() == player)
                .gameEnded(game.isGameEnded())
                .build();
    }
}
