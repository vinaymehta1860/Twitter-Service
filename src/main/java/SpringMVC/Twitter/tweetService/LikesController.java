package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.services.GraphQLService;
import SpringMVC.Twitter.tweetService.services.LikeService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikesController {
    @Autowired
    LikeService likeService;
    @Autowired
    GraphQLService graphQLService;

    // This API is not supposed to be used by anyone. This is just for testing purposes
    @RequestMapping("/likes")
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    // Get count of likes for a tweet
    @RequestMapping("/likes/{tweetId}")
    public long getLikesCountForTweet(@PathVariable long tweetId) {
        return likeService.getLikesCountForTweet(tweetId);
    }

    // Register like for a tweet by a user
    @RequestMapping(method = RequestMethod.POST, value = "/likes/{tweetId}/{userId}")
    public boolean registerLikeForTweetByUser(@PathVariable long tweetId, @PathVariable long userId) {
        return likeService.registerLikeForTweetByUser(tweetId, userId);
    }

    // Remove like for a tweet by a user
    @RequestMapping(method = RequestMethod.DELETE, value = "/likes/{tweetId}/{userId}")
    public boolean deleteLikeForTweetByUser(@PathVariable long tweetId, @PathVariable long userId) {
        return likeService.deleteLikeForTweetByUser(tweetId, userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/graphql/likes")
    public ResponseEntity<Object> getLikes(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
