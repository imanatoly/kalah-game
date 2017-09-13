package net.kalah.repository;

import net.kalah.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// todo keep games on key value store
// todo clean game cache by game last update
public class GameRegistry {

    private Map<String, Game> activeGamesById = new HashMap<>();
    private Map<String, String> gameIdByPlayerId = new HashMap<>();

    public Game createGame(byte initialStoneCount) {
        String gameId = UUID.randomUUID().toString();
        String playerAID = UUID.randomUUID().toString();
        String playerBId = UUID.randomUUID().toString();
        Game game = new Game(gameId, playerAID, playerBId, initialStoneCount);
        activeGamesById.put(gameId, game);
        gameIdByPlayerId.put(playerAID, gameId);
        return game;
    }

    public Game getGameForPlayer(String playerId){
        String gameId = gameIdByPlayerId.get(playerId);
        if (gameId==null){
            throw new IllegalArgumentException("Game not found for player "+gameId+" Either id is invalid, or game is expired.");
        }
        return activeGamesById.get(gameId);
    }

    // @Scheduled
    public void expireGames(){
        // todo
    }

}
