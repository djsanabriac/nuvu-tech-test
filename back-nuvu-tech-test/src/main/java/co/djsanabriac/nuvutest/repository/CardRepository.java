package co.djsanabriac.nuvutest.repository;

import co.djsanabriac.nuvutest.model.entity.Card;
import co.djsanabriac.nuvutest.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    List<Card> findAllByUser(@NotNull User user);

}
