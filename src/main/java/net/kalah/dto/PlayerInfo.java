package net.kalah.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kalah.game.Player;

@AllArgsConstructor
@Getter
public class PlayerInfo {

    private Player player;
    private String playerId;
}
