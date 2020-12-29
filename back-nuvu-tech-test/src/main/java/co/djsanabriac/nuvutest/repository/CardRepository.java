package co.djsanabriac.nuvutest.repository;

import co.djsanabriac.nuvutest.model.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {
}
