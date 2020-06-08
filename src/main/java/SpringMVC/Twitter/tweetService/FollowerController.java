package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.services.FollowerService;
import SpringMVC.Twitter.tweetService.utility.JWTUtil;
import SpringMVC.Twitter.userService.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowerController {
    @Autowired
    FollowerService followerService;
    @Autowired
    JWTUtil jwtUtil;

    // Get list of users who this user follows
    @RequestMapping("/users/{userId}/tweets/followers")
    public List<UserDTO> getFolloweesForUser(@PathVariable String userId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return followerService.getFolloweesForUser(userId);
        } else {
            return null;
        }
    }

    // Check if user A follows user B
    @RequestMapping("/users/{userId}/tweets/followers/{followeeId}")
    public boolean checkIfUserAFollowsUserB(@PathVariable String userId, @PathVariable String followeeId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return followerService.doesUserAFollowsUserB(userId, followeeId);
        } else {
            return false;
        }
    }

    // Register a follow request
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets/followers/{followeeId}")
    public boolean registerFollowRequest(@PathVariable String userId, @PathVariable String followeeId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return followerService.registerFollower(userId, followeeId);
        } else {
            return false;
        }
    }

    // Remove a follower
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/followers/{followeeId}")
    public boolean removeFollowRequest(@PathVariable String userId, @PathVariable String followeeId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return followerService.removeFollower(userId, followeeId);
        } else {
            return false;
        }
    }
}
