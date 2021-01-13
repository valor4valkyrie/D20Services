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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("qa")
@WebMvcTest(PlayerInfoEndpoint.class)
@AutoConfigureMockMvc
public class PlayerInfoEndpointTest {

    private Environment env;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private AuthServices authServices;

    private static String CREATE_URL = "/player_endpoint/v1/create";
    private static String GET_URL = "/player_endpoint/v1/6";
    private static String UPDATE_URL = "/player_endpoint/v1/6/update";

    private Player player = new Player();
    private Gson gson = new Gson();

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
        player.setPlayerEmail("test@test.com");
        player.setPlayerFirstName("Testy");
        player.setPlayerLastName("McTester");
        player.setPlayerUserName("TestyMcTester");
        player.setPlayerPassword("1234");

        when(authServices.isAuthenticated(anyString())).thenReturn(true);
        when(playerService.createPlayerInfo(any(PlayerModel.class))).thenReturn(player);
        when(playerService.getPlayerInfo(anyInt(), anyString())).thenReturn(Optional.of(player));
        when(playerService.updatePlayerInfo(anyInt(), anyString(), any(PlayerModel.class)))
                .thenReturn(Optional.of(player));
    }

    @Test
    void createPlayerSuccessTest() throws Exception {
        mockMvc.perform(post(CREATE_URL)

                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().isCreated());
    }

    @Test
    void createPlayerUnauthorizedTest() throws Exception{
        when(authServices.isAuthenticated(anyString())).thenReturn(false);

        mockMvc.perform(post(CREATE_URL)
                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getPlayerSuccessTest() throws Exception {
        mockMvc.perform(get(GET_URL)
                .param("password", "1234")
                .header("JWT", getJWTKey()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getPlayerNoUserFoundTest() throws Exception{
        when(playerService.getPlayerInfo(anyInt(), anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get(GET_URL)
                .param("password", "1234")
                .header("JWT", getJWTKey()))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPlayerUnauthorizedTest() throws Exception{
        when(authServices.isAuthenticated(anyString())).thenReturn(false);

        mockMvc.perform(get(GET_URL)
                .param("password", "1234")
                .header("JWT", getJWTKey()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void putUpdatePlayerSuccessTest() throws Exception{
        mockMvc.perform(put(UPDATE_URL)
                .param("password", "1234")
                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void putUpdatePlayerNoUserFoundTest() throws Exception{
        when(playerService.updatePlayerInfo(anyInt(), anyString(), any(PlayerModel.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(put(UPDATE_URL)
                .param("password", "1234")
                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().isNoContent());
    }

    @Test
    void putUpdatePlayerNoAuthTest() throws Exception{
        when(authServices.isAuthenticated(anyString())).thenReturn(false);

        mockMvc.perform(put(UPDATE_URL)
                .param("password", "1234")
                .header("JWT", getJWTKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(player)))
                .andExpect(status().isUnauthorized());
    }
}
