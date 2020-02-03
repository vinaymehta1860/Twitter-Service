package SpringMVC.Twitter.userService;

import SpringMVC.Twitter.userService.DTO.UserDTO;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
            users.add(userDTO);
        });
        return users;
    }

    public User getUserObjectById(long id) {
        return userRepository.findUserById(id);
    }

    public UserDTO getUserDTOById(long id) {
        User user = userRepository.findUserById(id);
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
        return userDTO;
    }

    public User addUser(User user) {
        if(!userExistsById(user.getId()) && !userExistsByEmail(user.getEmail())) {
            User userToAdd = new User(user.getFirstname(), user.getLastname(), user.getEmail());
            userRepository.save(userToAdd);
            return userToAdd;
        } else
            return null;
    }

    public boolean userExistsById(long userId) {
        return userRepository.existsById(userId);
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail((email));
    }

    public boolean removeUser(long userId) {
        if (userExistsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else
            return false;
    }
}
