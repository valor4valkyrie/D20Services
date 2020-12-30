package application.player.service;

import application.auth.AuthServices;
import application.player.dto.Player;
import application.player.model.PlayerModel;
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

    public void createPlayerInfo(PlayerModel playerModel) {
        playerModel.setPlayerPassword(authServices.encryptPassword(playerModel.getPlayerPassword()));
        playerRepo.save(playerModel);
    }

    public Optional<Player> getPlayerInfo(Integer playerID, String password) {
        Optional<PlayerModel> playerModelOptional = playerRepo.findById(playerID);
        Optional<Player> playerOptional = Optional.empty();

        if (playerModelOptional.isPresent()) {
            if (!authServices.isValidPassword(password, playerModelOptional.get().getPlayerPassword())) {
                return Optional.empty();
            } else {
                return Optional.of(new Player(playerModelOptional.get()));
            }
        }

        return playerOptional;
    }

}
