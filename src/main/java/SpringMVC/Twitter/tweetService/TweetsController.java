package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.repositories.CommentRepository;
import SpringMVC.Twitter.tweetService.repositories.LikeRepository;
import SpringMVC.Twitter.tweetService.repositories.TweetRepository;
import SpringMVC.Twitter.tweetService.services.CommentService;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.tweetService.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
public class TweetsController {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    TweetService tweetService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;

    // This API is just for testing and should never be used on client side
    @RequestMapping("/tweets")
    public List<Tweet> getAllTweets() {
        return tweetService.findAllTweets();
    }

    @RequestMapping("/tweets/{userId}")
    public List<Tweet> getTweetsByUserId(@PathVariable long userId) {
        return tweetService.getTweetsByUserId(userId);
    }

    @RequestMapping("/tweets/{userId}/{tweetId}")
    public Tweet getUserTweetsById(@PathVariable long userId, @PathVariable long tweetId) {
        return tweetService.getUserTweetById(userId, tweetId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets/{userId}")
    public Tweet addTweet(@RequestBody Tweet tweet, @PathVariable long userId) {
        return tweetService.addTweet(userId, tweet);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tweets/{userId}/{tweetId}")
    public Tweet updateTweet(@PathVariable long userId, @PathVariable long tweetId, @RequestBody Tweet tweet) {
        return tweetService.updateUserTweet(userId, tweetId, tweet);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/{userId}/{tweetId}")
    public boolean removeTweet(@PathVariable long userId, @PathVariable long tweetId) {
        return tweetService.removeTweet(userId, tweetId);
    }
}