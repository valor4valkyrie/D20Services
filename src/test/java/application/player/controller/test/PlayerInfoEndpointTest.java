package application.player.controller.test;

import application.auth.AuthServices;
import application.player.controller.PlayerInfoEndpoint;
import application.player.dto.Player;
import application.player.model.PlayerModel;
import application.player.service.PlayerService;
import com.google.gson.Gson;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("qa")
@WebMvcTest(PlayerInfoEndpoint.class)
public class PlayerInfoEndpointTest {

    private Environment env;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private AuthServices authServices;

    @Autowired
    public PlayerInfoEndpointTest(Environment env) {
        this.env = env;
    }

    private String getJWTKey() {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        return textEncryptor.encrypt(env.getProperty("security.jwt.password") + "-" + LocalDateTime.now());
    }

    @BeforeEach
    void setupTests() {
        when(authServices.isAuthenticated(anyString())).thenReturn(true);
        when(playerService.createPlayerInfo(any(PlayerModel.class))).thenReturn(new PlayerModel());
        when(playerService.getPlayerInfo(anyInt(), anyString())).thenReturn(Optional.of(new Player()));
    }

    @Test
    void createUserSuccessTest() throws Exception{
        Player player = new Player();
        Gson gson = new Gson();
        System.out.println(gson.toJson(player));
        mockMvc.perform(post("/player/create")
                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().isCreated());
    }

    @Test
    void createUserUnauthorizedTest() throws Exception{
        when(authServices.isAuthenticated(anyString())).thenReturn(false);

        Player player = new Player();
        Gson gson = new Gson();
        System.out.println(gson.toJson(player));
        mockMvc.perform(post("/player/create")
                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getUserSuccessTest() throws Exception {
        mockMvc.perform(get("/player/6")
                .param("password", "1234")
                .header("JWT", getJWTKey()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getUserNoUserFoundTest() throws Exception{
        when(playerService.getPlayerInfo(anyInt(), anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/player/6")
                .param("password", "1234")
                .header("JWT", getJWTKey()))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserUnauthorizedTest() throws Exception{
        when(authServices.isAuthenticated(anyString())).thenReturn(false);

        mockMvc.perform(get("/player/6")
                .param("password", "1234")
                .header("JWT", getJWTKey()))
                .andExpect(status().isUnauthorized());
    }
}
