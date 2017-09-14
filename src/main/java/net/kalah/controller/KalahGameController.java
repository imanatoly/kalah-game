package net.kalah.controller;

import lombok.extern.slf4j.Slf4j;
import net.kalah.converter.ConversionService;
import net.kalah.dto.GameDto;
import net.kalah.dto.GameStatusDto;
import net.kalah.dto.PlayerInfo;
import net.kalah.game.Game;
import net.kalah.game.Player;
import net.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/api/kalah", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class KalahGameController {

    private GameService gameService;
    private ConversionService converter;

    @Autowired
    public KalahGameController(GameService gameService, ConversionService converter) {
        this.gameService = gameService;
        this.converter = converter;
    }

    @GetMapping(value = "/join")
    public PlayerInfo joinGame() {
        log.info("Join called");

        return gameService.join();
    }

    @PutMapping(value = "/game/{id}/slot/{slotIndex}")
    public GameDto play(@PathVariable String id, @PathVariable byte slotIndex) {
        log.info("Recieved play id {} slot {}", id, slotIndex);
        Game game = gameService.play(id, slotIndex);
        return converter.convert(game, id);
    }

    @GetMapping(path = "/game/{id}/status")
    public GameStatusDto getStatus(@PathVariable String id){
        log.info("Recieved get status id {} ", id);
        Game game = gameService.getGame(id);
        Player player = game.getPlayerById(id);
        return converter.convert(game.getStatus(), player);
    }

    @GetMapping(path = "/game/{id}")
    public GameDto getGame(@PathVariable String id){
        log.info("Recieved get game id {} ", id);
        Game game = gameService.getGame(id);
        return converter.convert(game, id);
    }

}
