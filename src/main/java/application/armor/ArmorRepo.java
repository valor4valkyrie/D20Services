package application.armor;

import application.armor.model.ArmorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorRepo extends CrudRepository<ArmorModel, Integer> {
}
