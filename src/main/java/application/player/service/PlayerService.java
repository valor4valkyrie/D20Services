package application.player.service;

import application.auth.AuthServices;
import application.player.dto.Player;
import application.player.model.PlayerModel;
import application.verification.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo playerRepo;
    private AuthServices authServices;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, AuthServices authServices) {
        this.playerRepo = playerRepo;
        this.authServices = authServices;
    }

    public Player createPlayerInfo(PlayerModel playerModel) {
        if(!Verification.verifyEmail(playerModel.getPlayerEmail())) return new Player();
        playerModel.setPlayerPassword(authServices.encryptPassword(playerModel.getPlayerPassword()));
        return new Player(playerRepo.save(playerModel));
    }

    public Optional<Player> getPlayerInfo(Integer playerID, String password) {
        Optional<PlayerModel> playerModelOptional = playerRepo.findById(playerID);

        if (playerModelOptional.isPresent()) {
            if (!authServices.isValidPassword(password, playerModelOptional.get().getPlayerPassword())) {
                return Optional.empty();
            } else {
                return Optional.of(new Player(playerModelOptional.get()));
            }
        }

        return Optional.empty();
    }

    public Optional<Player> updatePlayerInfo(Integer playerID, String password, PlayerModel playerModel) {
        Optional<PlayerModel> playerModelOptional = playerRepo.findById(playerID);

        if (playerModelOptional.isPresent()) {
            if (!authServices.isValidPassword(password, playerModelOptional.get().getPlayerPassword())) {
                return Optional.empty();
            } else {
                playerModel.setId(playerID);
                return Optional.of(new Player(playerRepo.save(playerModel)));
            }
        }

        return Optional.empty();
    }

}
