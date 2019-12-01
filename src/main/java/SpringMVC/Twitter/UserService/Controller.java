package SpringMVC.Twitter.UserService;

import SpringMVC.Twitter.UserService.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public List<User> getUsers() {
        List<User> list = new ArrayList();
        userRepository.findAll().forEach(user -> list.add(user));
        return list;
    }

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        User user = userRepository.findById(id);
        if(user != null)
            return user;
        else
            return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public boolean generateUser(@RequestBody User user) {
        if(!userRepository.existsByEmail(user.getEmail())) {
            User u = new User(user.getFirstname(), user.getLastname(), user.getEmail());
            userRepository.save(u);
            return true;
        } else
            return false;
    }
}
