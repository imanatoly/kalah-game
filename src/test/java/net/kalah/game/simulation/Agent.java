package net.kalah.game.simulation;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import net.kalah.dto.PlayerInfo;
import net.kalah.game.GameStatus;
import net.kalah.game.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static net.kalah.game.GameStatus.CREATED;
import static net.kalah.game.GameStatus.ENDED;
import static net.kalah.game.GameStatus.TURN_A;
import static net.kalah.game.GameStatus.TURN_B;
import static net.kalah.game.Player.A;

public class Agent implements Runnable {

    private KalahGameAccessor accessor = Feign.builder().decoder(new GsonDecoder()).target(KalahGameAccessor.class, "http://localhost:8080");

    public void run(){
        PlayerInfo playerInfo = accessor.joinGame();
        Player mySide = playerInfo.getPlayer();
        GameStatus myTurn = (mySide==A) ? TURN_A : TURN_B;
        Random random = new Random();
        GameStatus status = CREATED;

        while (status!=ENDED) {
            while ((status = accessor.getStatus(playerInfo.getId())) != myTurn) {
                try {
                    Thread.sleep(100);
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
            accessor.play(playerInfo.getId(), random.nextInt(6));
        }
    }

}
