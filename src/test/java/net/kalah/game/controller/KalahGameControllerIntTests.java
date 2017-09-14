package net.kalah.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kalah.dto.PlayerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static net.kalah.game.Player.A;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class KalahGameControllerIntTests {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @PostConstruct
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DirtiesContext
    public void shouldJoinGame() throws Exception {
        MockHttpServletRequestBuilder getRequest = get("/api/kalah/join");
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player").value(A.name()))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();
    }

    @Test
    @DirtiesContext
    public void shouldGetGame() throws Exception {
        MockHttpServletRequestBuilder joinRequest = get("/api/kalah/join");
        String responseString = mockMvc.perform(joinRequest).andReturn().getResponse().getContentAsString();
        PlayerInfo playerInfo = objectMapper.readValue(responseString, PlayerInfo.class);

        MockHttpServletRequestBuilder getGameRequest = get("/api/kalah/game/{id}", playerInfo.getId());
        mockMvc.perform(getGameRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").value(playerInfo.getId()))
                .andReturn();
    }

    @Test
    @DirtiesContext
    public void shouldPlayGame() throws Exception {

        MockHttpServletRequestBuilder joinRequest = get("/api/kalah/join");
        String responseString = mockMvc.perform(joinRequest).andReturn().getResponse().getContentAsString();
        PlayerInfo playerInfo = objectMapper.readValue(responseString, PlayerInfo.class);

        // pair with another player to start
        MockHttpServletRequestBuilder joinRequest1 = get("/api/kalah/join");
        mockMvc.perform(joinRequest1).andReturn();


        MockHttpServletRequestBuilder playRequest = put("/api/kalah/game/{id}/slot/{slotId}", playerInfo.getId(), 0);
        mockMvc.perform(playRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").value(playerInfo.getId()))
                .andExpect(jsonPath("$.score").value(1))
                .andReturn();
    }
}
