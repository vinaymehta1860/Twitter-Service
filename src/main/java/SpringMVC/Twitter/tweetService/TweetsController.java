package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.services.CommentService;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.tweetService.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping("/tweets")
    public List<TweetDTO> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @RequestMapping("users/{userId}/tweets")
    public List<TweetDTO> getAllTweetsForUser(@PathVariable long userId) {
        return tweetService.getTweetsForUser(userId);
    }

    @RequestMapping("users/{userId}/tweets/{tweetId}")
    public Tweet getUserTweetById(@PathVariable long userId, @PathVariable long tweetId) {
        return tweetService.getUserTweetById(userId, tweetId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets")
    public Tweet addTweet(@RequestBody Tweet tweet, @PathVariable long userId) {
        return tweetService.addTweet(userId, tweet);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "users/{userId}/tweets/{tweetId}")
    public Tweet updateTweet(@PathVariable long userId, @PathVariable long tweetId, @RequestBody Tweet tweet) {
        return tweetService.updateUserTweet(userId, tweetId, tweet);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "users/{userId}/tweets/{tweetId}")
    public boolean removeTweet(@PathVariable long userId, @PathVariable long tweetId) {
        return tweetService.removeTweet(userId, tweetId);
    }
}
