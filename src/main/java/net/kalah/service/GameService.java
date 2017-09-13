package net.kalah.service;


import net.kalah.dto.PlayerInfo;
import net.kalah.game.GameEngine;
import net.kalah.game.Game;
import net.kalah.game.Slot;
import net.kalah.game.Player;
import net.kalah.repository.GameRegistry;
import org.springframework.stereotype.Service;

import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;

@Service
public class GameService {

    private GameRegistry gameRegistry;
    private GameEngine engine;

    public GameService(GameRegistry gameRegistry, GameEngine engine) {
        this.gameRegistry = gameRegistry;
        this.engine = engine;
    }

    public PlayerInfo join(){
        // todo
        return null;
    }

    public Game getGame(String playerId){
        Game game = gameRegistry.getGameForPlayer(playerId);
        if (game==null){
            throw new IllegalArgumentException("Game not found for player id "+playerId);
        }
        return game;
    }

    public Game play(String playerId, byte cell){
        Game game = getGame(playerId);

        Player player = A;
        if (game.getPlayerBId().equals(playerId))
            player = B;

        Slot slot = Slot.instance(player, cell);

        engine.apply(game, slot);

        return game;
    }
}
