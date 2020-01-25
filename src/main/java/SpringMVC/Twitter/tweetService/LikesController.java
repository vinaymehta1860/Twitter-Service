package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.services.LikeService;
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

    @RequestMapping("/likes/{tweetId}")
    public long getLikesCountForTweet(@PathVariable long tweetId) {
        return likeService.getLikesCountForTweet(tweetId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/likes/{userId}/{tweetId}")
    public boolean registerLikeForTweetByUser(@PathVariable long userId, @PathVariable long tweetId) {
        return likeService.registerLikeForTweetByUser(userId, tweetId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/likes/{userId}/{tweetId}")
    public boolean removeLikeForTweetByUser(@PathVariable long userId, @PathVariable long tweetId) {
        return likeService.removeLikeForTweetByUser(userId, tweetId);
    }
}
