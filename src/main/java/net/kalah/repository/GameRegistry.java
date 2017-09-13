package net.kalah.repository;

import net.kalah.game.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// todo keep games on key value store
// todo clean game cache by game last update
@Component
public class GameRegistry {

    private Map<String, Game> activeGamesById = new HashMap<>();
    private Map<String, String> gameIdByPlayerId = new HashMap<>();

    public Game createGame(byte initialStoneCount) {
        Game game = new Game(initialStoneCount);
        activeGamesById.put(game.getId(), game);
        gameIdByPlayerId.put(game.getPlayerAId(), game.getId());
        gameIdByPlayerId.put(game.getPlayerBId(), game.getId());
        return game;
    }

    public Game getGameForPlayer(String playerId) {
        String gameId = gameIdByPlayerId.get(playerId);
        if (gameId == null) {
            throw new IllegalArgumentException("Game not found for player " + gameId + " Either id is invalid, or game is expired.");
        }
        return activeGamesById.get(gameId);
    }

    // @Scheduled
    public void expireGames() {
        // todo
    }

}
