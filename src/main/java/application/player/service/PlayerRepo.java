package application.player.service;

import application.player.model.PlayerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends CrudRepository<PlayerModel, Integer> {
}
