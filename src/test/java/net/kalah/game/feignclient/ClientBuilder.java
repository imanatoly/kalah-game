package net.kalah.game.feignclient;


import feign.Feign;
import feign.gson.GsonDecoder;
import feign.slf4j.Slf4jLogger;
import lombok.Getter;

@Getter
public class ClientBuilder {

    private KalahGameAccessor gameClient = Feign.builder()
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger())
            .target(KalahGameAccessor.class, "http://localhost:8080");


}
