package v1;

import application.stats.dto.Stats;
import application.stats.model.StatsModel;
import application.stats.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.client.Entity;
import java.util.Optional;

@RestController
public class CharacterEndpoint {

    @Autowired
    private StatService statService;

    @PutMapping(value = "/character/stats", produces = "application/json")
    public ResponseEntity<String> saveStatsEndpoint(@RequestHeader("JWT") String jwt, @RequestBody(required = true) Stats stats){

        statService.saveStats(Stats.toModel(Entity.json(stats).getEntity()));

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/character/stats", produces = "application/json")
    public ResponseEntity<StatsModel> getStatsEnpogint(@RequestParam(value="id") int id){
        Optional<StatsModel> statsModel = statService.getStats(id);

        if(statsModel.isPresent()){
            ResponseEntity entity = new ResponseEntity(statsModel, HttpStatus.OK);
            return entity;
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
