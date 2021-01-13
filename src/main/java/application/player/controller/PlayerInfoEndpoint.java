package application.player.controller;

import application.auth.AuthServices;
import application.player.dto.Player;
import application.player.model.PlayerModel;
import application.player.service.PlayerService;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/player_endpoint/v1")
public class PlayerInfoEndpoint {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private AuthServices authServices;
    private PlayerService playerService;

    @Autowired
    public PlayerInfoEndpoint(PlayerService playerService, AuthServices authServices) {
        this.authServices = authServices;
        this.playerService = playerService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestHeader("JWT") String jwt, @RequestBody Player player) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Player playerResult = playerService.createPlayerInfo(new PlayerModel(player));

        return new ResponseEntity(playerResult, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{playerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@RequestHeader("JWT") String jwt, @PathVariable(value = "playerID") Integer playerID,
                                  @RequestParam(value = "password") String password) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Optional<Player> playerOptional = playerService.getPlayerInfo(playerID, password);

        if (playerOptional.isPresent()) {
            return new ResponseEntity(playerOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{playerID}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestHeader("JWT") String jwt,
                                     @PathVariable(value = "playerID") Integer playerID,
                                     @RequestParam(value = "password") String password,
                                     @RequestBody Player player
    ) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Optional<Player> playerOptional = playerService.updatePlayerInfo(playerID, password, new PlayerModel(player));

        if (playerOptional.isPresent()) {
            return new ResponseEntity(playerOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
