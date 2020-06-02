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

    // Get a list of all users
    public List<UserDTO> findAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
            users.add(userDTO);
        });
        return users;
    }

    // Get a user by userId
    public User getUserObjectById(String id) {
        return userRepository.findUserById(id);
    }

    // Get a user by userId
    public UserDTO getUserDTOById(String id) {
        User user = userRepository.findUserById(id);
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
        return userDTO;
    }

    // Add a user
    public User addUser(UserDTO user) {
        if(!userExistsById(user.getId()) && !userExistsByEmail(user.getEmail())) {
            User userToAdd = new User(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
            userRepository.save(userToAdd);
            return userToAdd;
        } else
            return null;
    }

    // Check if a user with userId exists or not
    public boolean userExistsById(String userId) {
        return userRepository.existsById(userId);
    }

    // Check if user with email exists or not
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail((email));
    }

    // Remove a user
    public boolean deleteUser(String userId) {
        if (userExistsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else
            return false;
    }

    // Remove all users
    public void deleteAllUsers() {
        userRepository.deleteAll();
        return;
    }
}
