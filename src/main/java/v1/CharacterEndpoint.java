package v1;

import application.stats.model.StatsModel;
import application.stats.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class CharacterEndpoint {

    @Autowired
    private StatService statService;

    @PostMapping(value = "/test", produces = "application/json")
    public ResponseEntity<String> saveStatsEndpoint(@RequestParam(value="id", defaultValue="1") int id){
        Random random = new Random();
        StatsModel statsModel = new StatsModel();
        statsModel.setCharisma(random.nextInt(20));
        statsModel.setConstitution(random.nextInt(20));
        statsModel.setDexterity(random.nextInt(20));
        statsModel.setIntelligence(random.nextInt(20));
        statsModel.setStrength(random.nextInt(20));
        statsModel.setWisdom(random.nextInt(20));

        statService.saveStats(statsModel);

        Optional<StatsModel> modelStat = statService.getStats(id);

        if(modelStat.isPresent()){
            return new ResponseEntity(modelStat.get(), HttpStatus.OK);
        }

        return new ResponseEntity(id, HttpStatus.OK);
    }
}
