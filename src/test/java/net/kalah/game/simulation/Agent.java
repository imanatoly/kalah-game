package net.kalah.game.simulation;

import net.kalah.dto.BoardDto;
import net.kalah.dto.GameDto;
import net.kalah.dto.GameStatusDto;
import net.kalah.dto.PlayerInfo;
import net.kalah.game.feignclient.ClientBuilder;
import net.kalah.game.feignclient.KalahGameAccessor;

import java.util.Random;

import static java.text.MessageFormat.format;
import static net.kalah.dto.GameStatusDto.*;

public class Agent implements Runnable {

    private KalahGameAccessor gameAccessor = new ClientBuilder().getGameClient();

    private Random random = new Random();

    public void run() {
        PlayerInfo playerInfo = gameAccessor.joinGame();
        String id = playerInfo.getId();
        GameStatusDto status = WAITING_OPPONENT;

        while (status != ENDED) {
            try {
                status = gameAccessor.getStatus(id);
                System.out.println(format("{0} status {1}", id, status));
                while (status == OPPONENT_TURN) {
                    status = gameAccessor.getStatus(id);
                    //System.out.println(format("{0} status {1}", id, status));
                    try {
                        Thread.sleep(20);
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
                GameDto game = gameAccessor.getGame(id);
                print(game);
                int slot = random.nextInt(6);
                System.out.println(format("{0} Play {1}", id, slot));
                gameAccessor.play(id, slot);
            } catch (Exception x) {
                //System.out.println("ERR : "+x.getMessage());
            }

        }
    }

    private void print(GameDto game) {
        BoardDto board = game.getBoard();
        int[] o = board.getOpponentCells();
        int[] m = board.getOwnCells();
        System.out.println(String.format("(%02d) [%02d][%02d][%02d][%02d][%02d][%02d]", board.getOpponentKalah(), o[5], o[4], o[3], o[2], o[1], o[0]));
        System.out.println(String.format("     [%02d][%02d][%02d][%02d][%02d][%02d] (%02d)", m[0], m[1], m[2], m[3], m[4], m[5], board.getOwnKalah()));
    }

}
