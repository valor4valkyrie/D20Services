package application.player.service;

import application.player.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Integer;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    public PlayerService (){}

    public Optional<PlayerModel> getPlayerInfo(Integer id) {
        return playerRepo.findById(id);
    }

}
