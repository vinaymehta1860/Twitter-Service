package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.services.CommentService;
import SpringMVC.Twitter.tweetService.services.GraphQLService;
import SpringMVC.Twitter.tweetService.services.LikeService;
import SpringMVC.Twitter.tweetService.services.TweetService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
public class TweetsController {

    @Autowired
    TweetDTO tweetDTO;
    @Autowired
    TweetService tweetService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    GraphQLService graphQLService;

    // This API is just for testing and should never be used on client side
    @RequestMapping("/tweets")
    public List<TweetDTO> getAllTweets() {
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

    @RequestMapping(method = RequestMethod.POST, value = "/graphql/tweets")
    public ResponseEntity<Object> getTweets(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
