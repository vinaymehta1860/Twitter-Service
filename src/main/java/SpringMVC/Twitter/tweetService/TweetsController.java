package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.services.CommentService;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.tweetService.services.TweetService;
import SpringMVC.Twitter.tweetService.utility.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetsController {

    @Autowired
    TweetService tweetService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    JWTUtil jwtUtil;

    // This API is just for testing and should never be used on client side
    @RequestMapping("/users/tweets")
    public List<TweetDTO> getAllTweets() {
        return tweetService.getAllTweets();
    }

    // Get all tweets by a particular user
    @RequestMapping("/users/{userId}/tweets")
    public List<TweetDTO> getAllTweetsForUser(@PathVariable String userId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return tweetService.getTweetsForUser(userId);
        } else {
            return null;
        }
    }

    // Get a particular tweet by a particular user
    @RequestMapping("/users/{userId}/tweets/{tweetId}")
    public Tweet getUserTweetById(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return tweetService.getUserTweetById(userId, tweetId);
        } else {
            return null;
        }
    }

    // Add a tweet by a user
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets")
    public Tweet addTweet(@RequestBody Tweet tweet, @PathVariable String userId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return tweetService.addTweet(userId, tweet);
        } else {
            return null;
        }
    }

    // Update a tweet by a user
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/tweets/{tweetId}")
    public Tweet updateTweet(@PathVariable String userId, @PathVariable long tweetId, @RequestBody Tweet tweet, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return tweetService.updateUserTweet(userId, tweetId, tweet);
        } else {
            return null;
        }
    }

    // Delete a tweet by a user
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/{tweetId}")
    public boolean removeTweet(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return tweetService.removeTweet(userId, tweetId);
        } else {
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validateToken/{userId}")
    public boolean validateToken(@PathVariable String userId, @RequestHeader("Authorization") String access_token) {
        return jwtUtil.isAccessTokenValid(access_token, userId);
    }
}
