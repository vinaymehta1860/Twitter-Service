package SpringMVC.Twitter.userService;

import SpringMVC.Twitter.userService.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByEmail(String email);

    User findUserById(String id);
}
