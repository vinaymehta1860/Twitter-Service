package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.services.CommentService;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.tweetService.services.TweetService;
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

    // This API is just for testing and should never be used on client side
    @RequestMapping("/users/tweets")
    public List<TweetDTO> getAllTweets() {
        return tweetService.getAllTweets();
    }

    // Get all tweets by a particular user
    @RequestMapping("/users/{userId}/tweets")
    public List<TweetDTO> getAllTweetsForUser(@PathVariable String userId, @RequestHeader("Authorization") String token) {
        return tweetService.getTweetsForUser(userId, token);
    }

    // Get a particular tweet by a particular user
    @RequestMapping("/users/{userId}/tweets/{tweetId}")
    public Tweet getUserTweetById(@PathVariable String userId, @PathVariable long tweetId) {
        return tweetService.getUserTweetById(userId, tweetId);
    }

    // Add a tweets by a user
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets")
    public Tweet addTweet(@RequestBody Tweet tweet, @PathVariable String userId) {
        return tweetService.addTweet(userId, tweet);
    }

    // Update a tweet by a user
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/tweets/{tweetId}")
    public Tweet updateTweet(@PathVariable String userId, @PathVariable long tweetId, @RequestBody Tweet tweet) {
        return tweetService.updateUserTweet(userId, tweetId, tweet);
    }

    // Delete a tweet by a user
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/{tweetId}")
    public boolean removeTweet(@PathVariable String userId, @PathVariable long tweetId) {
        return tweetService.removeTweet(userId, tweetId);
    }
}
