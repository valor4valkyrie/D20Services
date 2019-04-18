package v1;

import application.stats.dto.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerInfoEndpoint {

    @PutMapping(value="/user", consumes = "application/json")
    public ResponseEntity<String> saveUser(@RequestBody Player player) {
        System.out.println(player);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
