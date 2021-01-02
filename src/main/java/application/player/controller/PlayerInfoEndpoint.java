package application.player.controller;

import application.auth.AuthServices;
import application.player.dto.Player;
import application.player.service.PlayerService;
import com.google.common.flogger.FluentLogger;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/player")
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
    public ResponseEntity<String> createUser(@RequestHeader("JWT") String jwt, @RequestBody Player player) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        playerService.createPlayerInfo(Player.toModel(player));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{playerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@RequestHeader("JWT") String jwt, @PathVariable(value = "playerID") Integer playerID,
                                          @RequestParam(value = "password") String password) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Optional<Player> playerOptional = playerService.getPlayerInfo(playerID, password);

        Gson gson = new Gson();

        if(playerOptional.isPresent()){
            return new ResponseEntity<>(gson.toJson(playerOptional.get()), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
