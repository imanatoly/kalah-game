package net.kalah.game.game;

import net.kalah.game.Game;
import net.kalah.game.GameEngine;
import net.kalah.game.board.Board;
import net.kalah.game.board.Slot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static net.kalah.Constants.EXPERT_GAME_STONE_COUNT;
import static net.kalah.game.GameStatus.*;
import static net.kalah.game.TestUtil.initBoard;
import static net.kalah.game.board.Slot.*;
import static org.junit.Assert.assertEquals;

public class GameEngineTests {

    @Spy
    @InjectMocks
    private GameEngine gameEngine;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldExecuteRulesForFirstMove() {
        // given
        Game game = new Game(EXPERT_GAME_STONE_COUNT);
        game.setStatus(TURN_A);
        int addedStones = EXPERT_GAME_STONE_COUNT + 1;
        int initialStones = EXPERT_GAME_STONE_COUNT;
        Board board = game.getBoard();

        // when
        gameEngine.play(game, A_1);

        // then
        assertEquals(TURN_B, game.getStatus());

        assertEquals(1, board.getStoneCount(KALAH_A));
        assertEquals(0, board.getStoneCount(KALAH_B));

        assertEquals(0, board.getStoneCount(A_1));
        assertEquals(addedStones, board.getStoneCount(A_2));
        assertEquals(addedStones, board.getStoneCount(A_3));
        assertEquals(addedStones, board.getStoneCount(A_4));
        assertEquals(addedStones, board.getStoneCount(A_5));
        assertEquals(addedStones, board.getStoneCount(B_0));
        assertEquals(initialStones, board.getStoneCount(B_1));
        assertEquals(initialStones, board.getStoneCount(B_2));
        assertEquals(initialStones, board.getStoneCount(B_3));
        assertEquals(initialStones, board.getStoneCount(B_4));
        assertEquals(initialStones, board.getStoneCount(B_5));

        assertEquals(1, game.getPlayerAScore());
        assertEquals(0, game.getPlayerBScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenMoveIsOnBlankSlot() {
        // given
        Game game = new Game(EXPERT_GAME_STONE_COUNT);
        initBoard(game.getBoard(), new int[]{0, 1, 1, 1, 1, 1, 10, 1, 1, 1, 1, 1, 1, 10});

        // when
        gameEngine.play(game, A_0);
    }

    @Test
    public void shouldNotSwitchTurnWhenMoveEndInKalah() {
        // given
        Game game = new Game(EXPERT_GAME_STONE_COUNT);
        game.setStatus(TURN_A);
        int initialScoreA = 10;
        initBoard(game.getBoard(), new int[]{0, 1, 1, 1, 1, 1, initialScoreA, 1, 1, 1, 1, 1, 1, 10});

        // when
        gameEngine.play(game, A_5);

        // then
        assertEquals(TURN_A, game.getStatus());
    }

    @Test
    public void shouldEndGameWhenOneSideIsBlank() {
        // given
        Game game = new Game(EXPERT_GAME_STONE_COUNT);
        int initialScoreA = 15;
        int initialScoreB = 14;
        initBoard(game.getBoard(), new int[]{0, 0, 0, 0, 0, 1, initialScoreA, 1, 1, 1, 1, 1, 1, initialScoreB});

        // when
        gameEngine.play(game, A_5);

        // then
        assertEquals(ENDED, game.getStatus());

        assertEquals(initialScoreA + 1, game.getPlayerAScore());
        assertEquals(initialScoreB + 6, game.getPlayerBScore()); // 1 stone in each slot of B
    }

    @Test
    public void shouldRewardOppositeStonesWhenPlayEndOnOwnEmptySlot() {
        // given
        Game game = new Game(EXPERT_GAME_STONE_COUNT);
        int initialScoreA = 15;
        int initialScoreB = 15;
        int rewardSlot = 10;
        initBoard(game.getBoard(), new int[]{1, 0, 0, 0, 1, 0, initialScoreA, rewardSlot, 1, 1, 1, 1, 1, initialScoreB});

        // when
        gameEngine.play(game, A_4);

        // then
        assertEquals(TURN_B, game.getStatus());

        assertEquals(initialScoreA + 1 + rewardSlot, game.getPlayerAScore());
        assertEquals(0, game.getBoard().getStoneCount(Slot.B_0));
    }


}
