package application.verification;

import application.player.dto.Player;
import org.springframework.stereotype.Service;


public class Verification {

    public static boolean verifyEmail(String email){
        return email.contains("@");
    }

    public static boolean verifyPlayerData(Player player) {
        return verifyEmail(player.getPlayerEmail());
    }

}
