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

    @RequestMapping("/tweets/users")
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping("/tweets/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUserObjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/users/{userId}")
    public boolean removeUser(@PathVariable long userId) {
        return userService.removeUser(userId);
    }
}
