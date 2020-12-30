package application.stats.controller;

import application.auth.AuthServices;
import application.stats.dto.Stats;
import application.stats.model.StatsModel;
import application.stats.service.StatService;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.client.Entity;
import java.util.Optional;

@RestController("/stats/v1/player/{playerID}")
public class StatsEndpoint {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private StatService statService;
    private AuthServices authServices;

    @Autowired
    public StatsEndpoint(StatService statService, AuthServices authServices) {
        this.statService = statService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> createStatsEndpoint(@RequestHeader("JWT") String jwt,
                                                      @PathVariable("playerID") Integer playerID,
                                                      @RequestBody(required = true) Stats stats) {

        if (authServices.isAuthenticated(jwt)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        statService.saveStats(Stats.toModel(playerID, Entity.json(stats).getEntity()));
        logger.atInfo().log("Character Saved");

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/stats/{statsID}/update", consumes = "application/json")
    public ResponseEntity<String> updateStatsEndpoint(@RequestHeader("JWT") String jwt,
                                                      @PathVariable("statsID") Integer statsID,
                                                      @PathVariable("playerID") Integer playerID,
                                                      @RequestBody(required = true) Stats stats) {

        if (authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        statService.saveStats(Stats.toModel(playerID, Entity.json(stats).getEntity()));
        logger.atInfo().log("Character Saved");

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/stats/{statsID}", produces = "application/json")
    public ResponseEntity<StatsModel> getStatsEndpoint(@RequestHeader("JWT") String jwt,
                                                       @RequestParam(value = "id") Integer id) {

        if (authServices.isAuthenticated(jwt)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<StatsModel> statsModel = statService.getStats(id);

        if (statsModel.isPresent()) {
            ResponseEntity entity = new ResponseEntity(statsModel, HttpStatus.OK);
            logger.atFine().log("Character Found");
            return entity;
        }

        logger.atInfo().log("Character NOT Found");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
