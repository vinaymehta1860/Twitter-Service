package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.repositories.LikeRepository;
import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserService userService;
    @Autowired
    TweetService tweetService;

    public List<Like> getAllLikes() {
        List<Like> likes = new ArrayList<>();
        likeRepository.findAll().forEach(like -> likes.add(like));
        return likes;
    }

    public long getLikesCountForTweet(long tweetId) {
        return likeRepository.countLikesByTweetId(tweetId);
    }

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
