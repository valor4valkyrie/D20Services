package v1;

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

@RestController
public class PlayerInfoEndpoint {

    @Autowired
    private Environment env;

    @GetMapping(value="/user", consumes = "application/json")
    public ResponseEntity<String> getUser(@RequestHeader("JWT") String jwt, @RequestParam(value = "id", required = true) String id,
                                          @RequestParam(value = "password", required = true) String password) {

        BCryptPasswordEncoder pwe = new BCryptPasswordEncoder(4098);

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        try{
            String decrypt = textEncryptor.decrypt(jwt);

            LocalDateTime tokenDate = LocalDateTime.parse(decrypt.substring(decrypt.indexOf("-") + 1));

            if(LocalDateTime.now().isBefore(tokenDate.plusSeconds(5))) {

                pwe.matches(password, textEncryptor.decrypt(password));
            }
        }catch(Exception e){

        }

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
