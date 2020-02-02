package SpringMVC.Twitter.userService;

import SpringMVC.Twitter.userService.DTO.UserDTO;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(long id);

    boolean existsByEmail(String email);
}
