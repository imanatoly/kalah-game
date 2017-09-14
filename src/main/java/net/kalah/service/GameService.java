package net.kalah.service;


import net.kalah.Constants;
import net.kalah.dto.PlayerInfo;
import net.kalah.game.Game;
import net.kalah.game.GameEngine;
import net.kalah.game.Player;
import net.kalah.game.board.Slot;
import net.kalah.repository.GameRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static net.kalah.game.GameStatus.WAITING_OPPONENT;
import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;

@Service
public class GameService {

    private GameRegistry gameRegistry;
    private GameEngine engine;

    private Game currentGame;

    @Autowired
    public GameService(GameRegistry gameRegistry, GameEngine engine) {
        this.gameRegistry = gameRegistry;
        this.engine = engine;
    }

    public synchronized PlayerInfo join() {
        PlayerInfo result = null;
        if (currentGame == null) {
            currentGame = gameRegistry.createGame(Constants.EXPERT_GEME_STONE_COUNT);
            result = new PlayerInfo(A, currentGame.getPlayerAId());
            currentGame.setStatus(WAITING_OPPONENT);
        } else {
            result = new PlayerInfo(B, currentGame.getPlayerBId());
            currentGame.startGame();
            currentGame = null;
        }

        return result;
    }

    public Game getGame(String playerId) {
        Game game = gameRegistry.getGameForPlayer(playerId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found for player id " + playerId);
        }
        return game;
    }

    public Game play(String playerId, byte cell) {
        Game game = getGame(playerId);

        Player player = A;
        if (game.getPlayerBId().equals(playerId))
            player = B;

        Slot slot = Slot.instance(player, cell);

        engine.play(game, slot);

        return game;
    }
}
