package SpringMVC.Twitter.UserService;

import SpringMVC.Twitter.UserService.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public boolean existsByEmail(String email);

    public User findById(long id);
}
