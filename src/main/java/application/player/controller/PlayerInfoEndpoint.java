package application.player.controller;

import application.auth.AuthServices;
import application.player.dto.Player;
import application.player.service.PlayerService;
import com.google.common.flogger.FluentLogger;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PlayerInfoEndpoint {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private AuthServices authServices;
    private PlayerService playerService;

    @Autowired
    public PlayerInfoEndpoint(PlayerService playerService, AuthServices authServices) {
        this.authServices = authServices;
        this.playerService = playerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@RequestHeader("JWT") String jwt, @PathVariable(value = "playerID", required = true) Integer playerID,
                                             @RequestBody(required = true) Player player) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        playerService.createPlayerInfo(Player.toModel(player));

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/player/{playerID}")
    public ResponseEntity<String> getUser(@RequestHeader("JWT") String jwt, @PathVariable(value = "playerID", required = true) Integer playerID,
                                          @RequestParam(value = "password", required = true) String password) {

        if (!authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Player> playerOptional = playerService.getPlayerInfo(playerID, password);

        Gson gson = new Gson();

        if(playerOptional.isPresent()){
            return new ResponseEntity<>(gson.toJson(playerOptional.get()), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
