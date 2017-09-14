package net.kalah.game.service;

import net.kalah.dto.PlayerInfo;
import net.kalah.game.Game;
import net.kalah.game.GameEngine;
import net.kalah.game.board.Slot;
import net.kalah.repository.GameRegistry;
import net.kalah.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.kalah.Constants.NORMAL_GAME_STONE_COUNT;
import static net.kalah.game.GameStatus.TURN_A;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class GameServiceTests {

    @Mock
    private GameRegistry gameRegistry;

    @Mock
    private GameEngine engine;

    @InjectMocks
    private GameService gameService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldJoinGame() {
        // given
        Game game = new Game(NORMAL_GAME_STONE_COUNT);
        given(gameRegistry.createGame(anyByte())).willReturn(game);

        // when
        PlayerInfo playerInfo1 = gameService.join();
        PlayerInfo playerInfo2 = gameService.join();

        // then
        assertNotNull(playerInfo1);
        assertNotNull(playerInfo2);
        assertEquals(game.getPlayerAId(), playerInfo1.getId());
        assertEquals(game.getPlayerBId(), playerInfo2.getId());
        assertEquals(game.getStatus(), TURN_A);
    }

    @Test
    public void shouldGetGame() {
        // given
        Game game = new Game(NORMAL_GAME_STONE_COUNT);
        given(gameRegistry.getGameForPlayer(anyString())).willReturn(game);

        // when
        Game gameReturned = gameService.getGame(game.getPlayerBId());

        // then
        verify(gameRegistry).getGameForPlayer(anyString());
        assertEquals(gameReturned, game);

    }

    @Test
    public void shouldPlayGame() {
        // given
        Game game = new Game(NORMAL_GAME_STONE_COUNT);

        given(gameRegistry.getGameForPlayer(game.getPlayerAId())).willReturn(game);

        // when
        gameService.play(game.getPlayerAId(), (byte) 0);

        // then
        verify(engine).play(game, Slot.A_0);
    }


}
