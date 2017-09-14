package net.kalah.game.feignclient;

import feign.Param;
import feign.RequestLine;
import net.kalah.dto.GameDto;
import net.kalah.dto.GameStatusDto;
import net.kalah.dto.PlayerInfo;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * feign client to access rest endpoint
 */
@FeignClient(name = "kalah-service", url = "http://localhost")
public interface KalahGameAccessor {

    @RequestLine("GET /api/kalah/join")
    PlayerInfo joinGame();

    @RequestLine("GET /api/kalah/game/{id}/status")
    GameStatusDto getStatus(@Param("id") String id);

    @RequestLine("GET /api/kalah/game/{id}")
    GameDto getGame(@Param("id") String id);

    @RequestLine("PUT /api/kalah/game/{id}/slot/{slotIndex}")
    GameDto play(@Param("id") String id, @Param("slotIndex") int slotIndex);

}
