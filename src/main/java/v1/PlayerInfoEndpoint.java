package v1;

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

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PlayerInfoEndpoint {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Autowired
    private Environment env;

    private List<String> profileList = Lists.newArrayList(env.getActiveProfiles());

    @GetMapping(value="/user", consumes = "application/json")
    public ResponseEntity<String> getUser(@RequestHeader("JWT") String jwt, @RequestParam(value = "id", required = true) String id,
                                          @RequestParam(value = "password", required = true) String password) {

        BCryptPasswordEncoder pwe = new BCryptPasswordEncoder(4098);

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        if(!profileList.contains("dev")) {
            try {
                String decrypt = textEncryptor.decrypt(jwt);

                LocalDateTime tokenDate = LocalDateTime.parse(decrypt.substring(decrypt.indexOf("-") + 1));

                if (LocalDateTime.now().isBefore(tokenDate.plusSeconds(5))) {

                    pwe.matches(password, textEncryptor.decrypt(password));
                }
            } catch (Exception e) {
                logger.atSevere().withCause(e).log("Could not decrypt token {}", e);
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
