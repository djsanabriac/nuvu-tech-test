package co.djsanabriac.nuvutest.repository;

import co.djsanabriac.nuvutest.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String email);

    List<User> findAllByUsernameOrEmailIsNull(User user);

}
