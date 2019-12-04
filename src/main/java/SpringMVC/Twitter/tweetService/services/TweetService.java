package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.repositories.TweetRepository;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserService userService;

    public List<Tweet> findAllTweets() {
        List<Tweet> tweets = new ArrayList<>();
        tweetRepository.findAll().forEach(tweet -> tweets.add(tweet));
        return tweets;
    }

    public List<Tweet> getTweetsByUserId(long userId) {
        List<Tweet> tweets = new ArrayList<>();
        tweetRepository.findAllByUserId(userId).forEach(tweet -> tweets.add(tweet));
        return tweets;
    }

    public Tweet getUserTweetById(long userId, long tweetId) {
        return tweetRepository.findAllByUserIdAndId(userId, tweetId);
    }

    public Tweet addTweet(long userId, Tweet tweet) {
        User user = userService.findUserById(userId);
        Tweet tweetToAdd = new Tweet(tweet.getTitle(), tweet.getContent(), user);
        tweetToAdd = tweetRepository.save(tweetToAdd);
        if (tweetToAdd != null)
            return tweetToAdd;
        else
            return null;
    }

    public Tweet updateUserTweet(long userId, long tweetId, Tweet tweet) {
        Tweet tweetToUpdate = getUserTweetById(userId, tweetId);

        if (tweetToUpdate != null) {
            tweetToUpdate.setTitle(tweet.getTitle());
            tweetToUpdate.setContent(tweet.getContent());
            tweetRepository.save(tweetToUpdate);
            return tweetToUpdate;
        } else
            return null;
    }

    public boolean removeTweet(long userId, long tweetId) {
        Tweet tweetToRemove = getUserTweetById(userId, tweetId);

        if (tweetToRemove != null) {
            tweetRepository.deleteById(tweetToRemove.getId());
            return true;
        } else
            return false;
    }
}
