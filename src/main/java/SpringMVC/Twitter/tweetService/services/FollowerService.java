package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.models.Follower;
import SpringMVC.Twitter.tweetService.repositories.FollowerRepository;
import SpringMVC.Twitter.userService.DTO.UserDTO;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerService {
    @Autowired
    FollowerRepository followerRepository;
    @Autowired
    UserService userService;

    // Get a list of followers for a particular user
    public List<UserDTO> getFollowersForUser(String followeeId) {
        List<Follower> followers = followerRepository.findAllByFollowerId(followeeId);
        return convertFollowerObjectsToFolloweesObject(followers);
    }

    // Get a list of users that follow a user
    public List<UserDTO> getFolloweesForUser(String followerId) {
        List<Follower> followers = followerRepository.findAllByFolloweeId(followerId);
        return convertFollowerObjectsToUserDTOs(followers);
    }

    // Check if one person follows the other
    public boolean doesUserAFollowsUserB(String userA, String userB) {
        return followerRepository.existsByFolloweeIdAndFollowerId(userB, userA);
    }

    // Register a follower
    public boolean registerFollower(String followerId, String followeeId) {
        User followee = userService.getUserObjectById(followeeId);
        User follower = userService.getUserObjectById(followerId);

        if (followee != null && follower != null) {
            Follower followerObject = new Follower(followee, follower);
            followerRepository.save(followerObject);
            return true;
        } else {
            return false;
        }
    }

    // Remove a follower
    public boolean removeFollower(String followerId, String followeeId) {
        if (followerRepository.existsByFolloweeIdAndFollowerId(followerId, followeeId)) {
            Follower follower = followerRepository.findByFolloweeIdAndFollowerId(followeeId, followerId);

            if (follower != null) {
                followerRepository.deleteById(follower.getId());
                return true;
            }
        }
        return false;
    }

    /*
     * Utility methods
     */

    // Convert a list of Follower objects to UserDTO
    private List<UserDTO> convertFollowerObjectsToUserDTOs(List<Follower> followers) {
        List<UserDTO> userDTOS = new ArrayList<>();

        for (Follower follower : followers) {
            UserDTO userDTO = new UserDTO(follower.getFollower().getId(), follower.getFollower().getFirstname(), follower.getFollower().getLastname(), follower.getFollower().getEmail());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    // Convert a list of Followers to followee
    public List<UserDTO> convertFollowerObjectsToFolloweesObject(List<Follower> followers) {
        List<UserDTO> userDTOS = new ArrayList<>();

        for (Follower follower : followers) {
            UserDTO userDTO = new UserDTO(follower.getFollowee().getId(), follower.getFollowee().getFirstname(), follower.getFollowee().getLastname(), follower.getFollowee().getEmail());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }
}
