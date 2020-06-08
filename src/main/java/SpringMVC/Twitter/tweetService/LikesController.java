package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.tweetService.utility.JWTUtil;
import SpringMVC.Twitter.userService.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikesController {
    @Autowired
    LikeService likeService;
    @Autowired
    JWTUtil jwtUtil;

    // This API is not supposed to be used by anyone. This is just for testing purposes
    @RequestMapping("/likes")
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    // Get count of likes for a tweet
    @RequestMapping("/users/{userId}/tweets/{tweetId}/likes")
    public long getLikesCountForTweet(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return likeService.getCountOfLikesForTweet(tweetId);
        } else {
            return -1;
        }
    }

    // Get all the users who have liked a tweet
    @RequestMapping("/users/{userId}/tweets/{tweetId}/likes")
    public List<UserDTO> getUsersWhoHaveLikedATweet(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return likeService.getUsersWhoHaveLikedATweet(userId, tweetId);
        } else {
            return null;
        }
    }

    // Register like for a tweet by a user
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets/{tweetId}/likes")
    public boolean registerLikeForTweetByUser(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return likeService.registerLikeForTweetByUser(userId, tweetId);
        } else {
            return false;
        }
    }

    // Remove like for a tweet by a user
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/{tweetId}/likes")
    public boolean deleteLikeForTweetByUser(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return likeService.deleteLikeForTweetByUser(userId, tweetId);
        } else {
            return false;
        }
    }
}
