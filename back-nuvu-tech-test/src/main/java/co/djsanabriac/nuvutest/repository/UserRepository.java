package co.djsanabriac.nuvutest.repository;

import co.djsanabriac.nuvutest.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
