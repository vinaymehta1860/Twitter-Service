package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Follower;
import SpringMVC.Twitter.tweetService.services.FollowerService;
import SpringMVC.Twitter.userService.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FollowerController {
    @Autowired
    FollowerService followerService;

    // Get a list of followees for a user
    @RequestMapping("/tweets/followers/{followeeId}")
    public List<UserDTO> getFolloweesForUser(@PathVariable long followeeId) {
        return followerService.getFolloweesForUser(followeeId);
    }

    // Check if user A follows user B
    @RequestMapping("/tweets/followers/{followerId}/{followeeId}")
    public boolean checkIfUserAFollowsUserB(@PathVariable long followerId, @PathVariable long followeeId) {
        return followerService.doesUserAFollowsUserB(followerId, followeeId);
    }

    // Register a follower request
    @RequestMapping(method = RequestMethod.POST, value = "/tweets/followers/{followerId}/{followeeId}")
    public boolean registerFollowRequest(@PathVariable long followerId, @PathVariable long followeeId) {
        return followerService.registerFollower(followerId, followeeId);
    }

    // Remove a follower
    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/followers/{followerId}/{followeeId}")
    public boolean removeFollowRequest(@PathVariable long followerId, @PathVariable long followeeId) {
        return followerService.removeFollower(followerId, followeeId);
    }
}
