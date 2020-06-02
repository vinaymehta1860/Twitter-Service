package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.userService.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class LikesController {
    @Autowired
    LikeService likeService;

    // This API is not supposed to be used by anyone. This is just for testing purposes
    @RequestMapping("/likes")
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    // Get count of likes for a tweet
    @RequestMapping("/tweets/{tweetId}/likes")
    public long getLikesCountForTweet(@PathVariable long tweetId) {
        return likeService.getCountOfLikesForTweet(tweetId);
    }

    // Get all the users who have liked a tweet
    @RequestMapping("/users/tweets/{tweetId}/likes")
    public List<UserDTO> getUsersWhoHaveLikedATweet(@PathVariable long tweetId) {
        return likeService.getUsersWhoHaveLikedATweet(tweetId);
    }

    // Register like for a tweet by a user
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets/{tweetId}/likes")
    public boolean registerLikeForTweetByUser(@PathVariable long tweetId, @PathVariable String userId) {
        return likeService.registerLikeForTweetByUser(tweetId, userId);
    }

    // Remove like for a tweet by a user
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/{tweetId}/likes")
    public boolean deleteLikeForTweetByUser(@PathVariable long tweetId, @PathVariable String userId) {
        return likeService.deleteLikeForTweetByUser(tweetId, userId);
    }
}
