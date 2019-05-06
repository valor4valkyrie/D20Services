package v1;

import application.stats.dto.Stats;
import application.stats.model.StatsModel;
import application.stats.service.StatService;
import com.google.common.flogger.FluentLogger;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.client.Entity;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class StatsEndpoint {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Autowired
    Environment env;

    @Autowired
    private StatService statService;

    @PutMapping(value = "/character/stats", produces = "application/json")
    public ResponseEntity<String> saveStatsEndpoint(@RequestHeader("JWT") String jwt, @RequestBody(required = true) Stats stats){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        try {

            String decrypt = textEncryptor.decrypt(jwt);

            LocalDateTime tokenDate = LocalDateTime.parse(decrypt.substring(decrypt.indexOf("-") + 1));

            if(LocalDateTime.now().isAfter(tokenDate.plusSeconds(5))) {
                logger.atSevere().log("Invalid token to save a character. {}", tokenDate);
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            logger.atSevere().withCause(e).log("Could not decrypt token {}", e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        statService.saveStats(Stats.toModel(Entity.json(stats).getEntity()));
        logger.atInfo().log("Character Saved");

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/character/stats", produces = "application/json")
    public ResponseEntity<StatsModel> getStatsEnpogint(@RequestHeader("JWT") String jwt, @RequestParam(value="id") int id){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        String g = textEncryptor.encrypt(env.getProperty("security.jwt.password") + "-" + LocalDateTime.now().toString());

        try {

            String decrypt = textEncryptor.decrypt(jwt);

            LocalDateTime tokenDate = LocalDateTime.parse(decrypt.substring(decrypt.indexOf("-") + 1));

            if(LocalDateTime.now().isAfter(tokenDate.plusSeconds(5))) {
                logger.atSevere().log("Expired Token");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            logger.atSevere().log("Error decrypting token");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Optional<StatsModel> statsModel = statService.getStats(id);

        if(statsModel.isPresent()){
            ResponseEntity entity = new ResponseEntity(statsModel, HttpStatus.OK);
            logger.atFine().log("Character Found");
            return entity;
        }

        logger.atInfo().log("Character NOT Found");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
