package application.stats.service;

import application.stats.model.StatsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatService {

    @Autowired
    StatsRepo statsRepo;

    public void saveStats(StatsModel stat){
        statsRepo.save(stat);
    }

    public Optional<StatsModel> getStats(int id) { return statsRepo.findById(id); }
}