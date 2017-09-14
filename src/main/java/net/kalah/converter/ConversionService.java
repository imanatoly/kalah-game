package net.kalah.converter;

import net.kalah.dto.GameDto;
import net.kalah.dto.GameStatusDto;
import net.kalah.game.Game;
import net.kalah.game.GameStatus;
import net.kalah.game.Player;
import org.springframework.stereotype.Component;

@Component("converter")
public class ConversionService {

    private GameToGameStatusConverter gameToGameStatusConverter = new GameToGameStatusConverter();
    private GameStatusToGameStatusDtoConverter gameStatusToGameStatusDtoConverter = new GameStatusToGameStatusDtoConverter();

    public GameDto convert(Game game, String playerId){
        return gameToGameStatusConverter.convert(game, playerId);
    }

    public GameStatusDto convert(GameStatus gameStatus, Player player){
        return gameStatusToGameStatusDtoConverter.convert(gameStatus, player);
    }


}
