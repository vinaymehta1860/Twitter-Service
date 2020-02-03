package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.DTO.CommentDTO;
import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.repositories.TweetRepository;
import SpringMVC.Twitter.userService.DTO.UserDTO;
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
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    public List<TweetDTO> getAllTweets() {
        List<TweetDTO> tweets = new ArrayList<>();

        tweetRepository.findAll().forEach(tweet -> {
            // Get the UserDTO for this tweet
            UserDTO userDTO = userService.getUserDTOById(tweet.getUser().getId());
            // Get the likes count for this tweet
            long likesCountForTweet = likeService.getLikesCountForTweet(tweet.getId());
            // Get all the comments for this tweet
            List<CommentDTO> commentDTOS = commentService.getAllCommentsForTweet(tweet.getId());
            // Create the tweet DTO to be sent back
            TweetDTO tweetToAdd = new TweetDTO(tweet.getId(), tweet.getTitle(), tweet.getContent(), userDTO.getFirstname() + " " + userDTO.getLastname(), likesCountForTweet, commentDTOS);
            tweets.add(tweetToAdd);
        });

        return tweets;
    }

    public Tweet findTweetById(long tweetId) {
        return tweetRepository.findById(tweetId);
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
        User user = userService.getUserObjectById(userId);
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
        }

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
