package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.repositories.LikeRepository;
import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserService userService;
    @Autowired
    TweetService tweetService;

    // Get like objects for every tweet
    public List<Like> getAllLikes() {
        List<Like> likes = new ArrayList<>();
        likeRepository.findAll().forEach(like -> likes.add(like));
        return likes;
    }

    // Get the number of likes for one tweet
    public long getLikesCountForTweet(long tweetId) {
        return likeRepository.countLikesByTweetId(tweetId);
    }

    // Get number of likes for list of tweets
    public Hashtable<Long, Long> getLikesCountForTweets(long[] tweetIds) {
        Hashtable<Long, Long> likes = new Hashtable<>();

        for (long tweetId : tweetIds) {
            long like = getLikesCountForTweet(tweetId);
            likes.put(tweetId, like);
        }

        return likes;
    }

    // Register like for a tweet
    public boolean registerLikeForTweetByUser(long tweetId, long userId) {
        User user = userService.findUserById(userId);
        Tweet tweet = tweetService.findTweetById(tweetId);

        if (user != null && tweet != null) {
            Like like = new Like(tweet, user);
            if (like != null) {
                likeRepository.save(like);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Remove like for a tweet
    public boolean deleteLikeForTweetByUser(long tweetId, long userId) {
        try {
            long likeId = likeRepository.findByTweetIdAndUserId(tweetId, userId).getId();
            likeRepository.deleteById(likeId);
            return true;
        } catch(Exception e) {
            System.out.println("Exception: " + e.toString());
            return false;
        }
    }
}
