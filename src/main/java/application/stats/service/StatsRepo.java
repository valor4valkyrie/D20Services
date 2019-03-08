package application.stats.service;

import application.stats.model.StatsModel;
import org.springframework.data.repository.CrudRepository;

public interface StatsRepo extends CrudRepository<StatsModel, Integer> {

}
