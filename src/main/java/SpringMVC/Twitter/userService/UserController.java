package SpringMVC.Twitter.userService;

import SpringMVC.Twitter.userService.DTO.UserDTO;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // NEVER TO BE USED BY CLIENTS. JUST FOR TEST PURPOSES
    @RequestMapping("/tweets/users")
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping("/tweets/users/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUserObjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets/users")
    public User addUser(@RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/users/{userId}")
    public boolean removeUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    // NEVER TO BE USED BY CLIENTS. JUST FOR TEST PURPOSES
    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/users")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
}
