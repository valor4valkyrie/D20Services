package application.player.service;

import application.auth.AuthServices;
import application.player.dto.Player;
import application.player.model.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class PlayerServiceTest {

    private PlayerService playerService;
    private PlayerRepo playerRepo;
    private AuthServices authServices;

    private PlayerModel playerModel = new PlayerModel();

    @BeforeEach
    void testSetup() {
        playerModel = new PlayerModel();
        playerModel.setId(1234);
        playerModel.setPlayerFirstName("Testy");
        playerModel.setPlayerMiddleName("Clemont");
        playerModel.setPlayerLastName("McTester");
        playerModel.setPlayerUserName("BestyTesty");
        playerModel.setPlayerEmail("test@test.com");
        playerModel.setPlayerPassword("8675309");

        playerRepo = Mockito.mock(PlayerRepo.class);
        authServices = Mockito.mock(AuthServices.class);

        when(playerRepo.save(any(PlayerModel.class))).thenReturn(playerModel);
        when(playerRepo.findById(anyInt())).thenReturn(Optional.of(playerModel));
        when(authServices.encryptPassword(anyString())).thenCallRealMethod();
        when(authServices.isValidPassword(anyString(), anyString())).thenReturn(true);

        playerService = new PlayerService(playerRepo, authServices);
    }

    @Test
    void createPlayerInfoSuccessTest() {
        Player player = playerService.createPlayerInfo(playerModel);

        assertEquals(player.getPlayerFirstName(), "Testy");
        assertEquals(player.getPlayerMiddleName(), "Clemont");
        assertEquals(player.getPlayerLastName(), "McTester");
        assertEquals(player.getPlayerUserName(), "BestyTesty");
        assertEquals(player.getPlayerEmail(), "test@test.com");
        assertNull(player.getPlayerPassword());
    }

    @Test
    void getPlayerInfoSuccessTest() {
        Optional<Player> playerOptional = playerService.getPlayerInfo(1234, "abcde");

        assertTrue(playerOptional.isPresent());
        assertEquals(playerOptional.get().getPlayerFirstName(), "Testy");
        assertEquals(playerOptional.get().getPlayerMiddleName(), "Clemont");
        assertEquals(playerOptional.get().getPlayerLastName(), "McTester");
        assertEquals(playerOptional.get().getPlayerUserName(), "BestyTesty");
        assertEquals(playerOptional.get().getPlayerEmail(), "test@test.com");
        assertNull(playerOptional.get().getPlayerPassword());
    }

    @Test
    void getPlayerNotFoundTest() {
        when(playerRepo.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Player> playerOptional = playerService.getPlayerInfo(1234, "abcde");

        assertFalse(playerOptional.isPresent());
    }

    @Test
    void getPlayerInvalidPasswordTest() {
        when(authServices.isValidPassword(anyString(), anyString())).thenReturn(false);

        Optional<Player> playerOptional = playerService.getPlayerInfo(1234, "abcde");

        assertFalse(playerOptional.isPresent());
    }

    @Test
    void updatePlayerInfoSuccessTest() {
        Optional<Player> playerOptional = playerService.updatePlayerInfo(1234, "abcd", playerModel);

        assertTrue(playerOptional.isPresent());
        assertEquals(playerOptional.get().getPlayerFirstName(), "Testy");
        assertEquals(playerOptional.get().getPlayerMiddleName(), "Clemont");
        assertEquals(playerOptional.get().getPlayerLastName(), "McTester");
        assertEquals(playerOptional.get().getPlayerUserName(), "BestyTesty");
        assertEquals(playerOptional.get().getPlayerEmail(), "test@test.com");
        assertNull(playerOptional.get().getPlayerPassword());
    }

    @Test
    void updatePlayerNotFoundTest() {
        when(playerRepo.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Player> playerOptional = playerService.updatePlayerInfo(1234, "abcd", playerModel);

        assertFalse(playerOptional.isPresent());
    }

    @Test
    void updatePlayerInvalidPasswordTest() {
        when(authServices.isValidPassword(anyString(), anyString())).thenReturn(false);

        Optional<Player> playerOptional = playerService.updatePlayerInfo(1234, "abcd", playerModel);

        assertFalse(playerOptional.isPresent());
    }

}