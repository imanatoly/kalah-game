package net.kalah.controller;

import net.kalah.converter.GameToGameStatusConverter;
import net.kalah.dto.GameDto;
import net.kalah.dto.PlayerInfo;
import net.kalah.game.Game;
import net.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/kalah", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class KalahGameController {

    private GameService gameService;
    private GameToGameStatusConverter converter;

    @Autowired
    public KalahGameController(GameService gameService, GameToGameStatusConverter converter) {
        this.gameService = gameService;
        this.converter = converter;
    }

    @GetMapping(value = "/join")
    public PlayerInfo joinGame() {
        return gameService.join();
    }

    @GetMapping(value = "/player/{playerId}/play/{cellIndex}")
    public GameDto play(@PathVariable String playerId, @PathVariable byte cellIndex) {
        Game game = gameService.play(playerId, cellIndex);

        return converter.convert(game, playerId);
    }
}
