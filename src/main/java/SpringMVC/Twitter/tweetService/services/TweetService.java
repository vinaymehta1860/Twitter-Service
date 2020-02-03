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
import java.util.Arrays;
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

    // Get a list of all tweets (NOT TO USED BY CLIENTS)
    public List<TweetDTO> getAllTweets() {
        List<Tweet> tweets = (List<Tweet>) tweetRepository.findAll();

        return convertTweetObjectsToDTOList(tweets);
    }

    // Get a tweet by it's id (NOT TO USED BY CLIENTS)
    public TweetDTO getTweetById(long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId);

        return convertTweetObjectsToDTOList(Arrays.asList(tweet)).get(0);
    }

    // Get tweet entity object by id (NOT TO USED BY CLIENTS)
    public Tweet getTweetObjectById(long tweetId) {
        return tweetRepository.findById(tweetId);
    }

    // Get list of tweets for a user
    public List<TweetDTO> getTweetsByUserId(long userId) {
        List<Tweet> tweets = tweetRepository.findAllByUserId(userId);

        return convertTweetObjectsToDTOList(tweets);
    }

    // Get a tweet by it's id for a user
    public Tweet getUserTweetById(long userId, long tweetId) {
        return tweetRepository.findAllByUserIdAndId(userId, tweetId);
    }

    // Add a tweet
    public Tweet addTweet(long userId, Tweet tweet) {
        User user = userService.getUserObjectById(userId);
        Tweet tweetToAdd = new Tweet(tweet.getTitle(), tweet.getContent(), user);
        tweetToAdd = tweetRepository.save(tweetToAdd);

        if (tweetToAdd != null)
            return tweetToAdd;
        else
            return null;
    }

    // Update a tweet
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

    // Remove a tweet
    public boolean removeTweet(long userId, long tweetId) {
        Tweet tweetToRemove = getUserTweetById(userId, tweetId);

        if (tweetToRemove != null) {
            tweetRepository.deleteById(tweetToRemove.getId());
            return true;
        } else
            return false;
    }

    /*
     * Private Utility methods
     */

    // Function to convert Tweet Entity Objects to List of TweetDTOs
    public List<TweetDTO> convertTweetObjectsToDTOList(List<Tweet> tweets) {
        List<TweetDTO> tweetDTOS = new ArrayList<>();

        for (Tweet tweet : tweets) {
            // Get the UserDTO for this tweet
            UserDTO userDTO = userService.getUserDTOById(tweet.getUser().getId());
            // Get the likes count for this tweet
            long likesCountForTweet = likeService.getCountOfLikesForTweet(tweet.getId());
            // Get all the comments for this tweet
            List<CommentDTO> commentDTOS = commentService.getAllCommentsForTweet(tweet.getId());
            // Create the tweet DTO to be sent back
            TweetDTO tweetDTO = new TweetDTO(tweet.getId(), tweet.getTitle(), tweet.getContent(), userDTO.getFirstname() + " " + userDTO.getLastname(), likesCountForTweet, commentDTOS);
            tweetDTOS.add(tweetDTO);
        }

        return tweetDTOS;
    }
}
