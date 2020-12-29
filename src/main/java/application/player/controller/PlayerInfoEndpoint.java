package application.player.controller;

import application.player.model.PlayerModel;
import application.player.service.PlayerService;
import com.google.common.flogger.FluentLogger;
import org.assertj.core.util.Lists;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.Integer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class PlayerInfoEndpoint {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Autowired
    private Environment env;

    @Autowired
    private PlayerService playerService;

    @GetMapping(value="/user")
    public ResponseEntity<String> getUser(@RequestHeader("JWT") String jwt, @RequestParam(value = "id", required = true) Integer id,
                                          @RequestParam(value = "password", required = true) String password) {

        BCryptPasswordEncoder pwe = new BCryptPasswordEncoder();

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        List<String> profileList = Lists.newArrayList(env.getActiveProfiles());

        if(!profileList.contains("dev")) {
            try {
                String decrypt = textEncryptor.decrypt(jwt);

                LocalDateTime tokenDate = LocalDateTime.parse(decrypt.substring(decrypt.indexOf("-") + 1));

                if (LocalDateTime.now().isAfter(tokenDate.plusSeconds(5))) {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                logger.atSevere().withCause(e).log("Could not decrypt token {}", e);
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        Optional<PlayerModel> playerOptional = playerService.getPlayerInfo(id);
        if(playerOptional.isPresent()) {
            if(playerOptional.get().getPlayerPassword().matches(textEncryptor.decrypt(password))) {
            }
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
