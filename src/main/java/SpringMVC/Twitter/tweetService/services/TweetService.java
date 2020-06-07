package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.DTO.CommentDTO;
import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.repositories.TweetRepository;
import SpringMVC.Twitter.tweetService.utility.JWTUtil;
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
    FollowerService followerService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    JWTUtil jwtUtil;

    // Get a list of all tweets (NOT TO BE USED BY CLIENTS)
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
    public List<TweetDTO> getTweetsForUser(String userId, String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            List<TweetDTO> tweetDTOS = new ArrayList<>();

            List<UserDTO> followers = followerService.getFollowersForUser(userId);

            followers.forEach(follower -> {
                List<TweetDTO> tweets = getTweetsByUser(follower.getId());
                tweetDTOS.addAll(tweets);
            });

            return tweetDTOS;
        } else {
            return null;
        }
    }

    // Get a tweet by it's id for a user
    public Tweet getUserTweetById(String userId, long tweetId, String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return tweetRepository.findAllByUserIdAndId(userId, tweetId);
        } else {
            return null;
        }
    }

    // Add a tweet
    public Tweet addTweet(String userId, Tweet tweet, String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            User user = userService.getUserObjectById(userId);
            Tweet tweetToAdd = new Tweet(tweet.getTitle(), tweet.getContent(), user);
            tweetToAdd = tweetRepository.save(tweetToAdd);

            if (tweetToAdd != null)
                return tweetToAdd;
            else
                return null;
        } else {
            return null;
        }
    }

    // Update a tweet
    public Tweet updateUserTweet(String userId, long tweetId, Tweet tweet, String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            Tweet tweetToUpdate = tweetRepository.findAllByUserIdAndId(userId, tweetId);

            if (tweetToUpdate != null) {
                tweetToUpdate.setTitle(tweet.getTitle());
                tweetToUpdate.setContent(tweet.getContent());
                tweetRepository.save(tweetToUpdate);
                return tweetToUpdate;
            }

            return null;
        } else {
            return null;
        }
    }

    // Remove a tweet
    public boolean removeTweet(String userId, long tweetId, String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            Tweet tweetToRemove = tweetRepository.findAllByUserIdAndId(userId, tweetId);

            if (tweetToRemove != null) {
                tweetRepository.deleteById(tweetToRemove.getId());
                return true;
            } else
                return false;
        } else {
            return false;
        }
    }

    /*
     * Private Utility methods
     */

    // Get list of tweets for a user
    private List<TweetDTO> getTweetsByUser(String userId) {
        List<Tweet> tweets = tweetRepository.findAllByUserId(userId);

        return convertTweetObjectsToDTOList(tweets);
    }

    // Function to convert Tweet Entity Objects to List of TweetDTOs
    private List<TweetDTO> convertTweetObjectsToDTOList(List<Tweet> tweets) {
        List<TweetDTO> tweetDTOS = new ArrayList<>();

        for (Tweet tweet : tweets) {
            // Get the UserDTO for this tweet
            UserDTO userDTO = userService.getUserDTOById(tweet.getUser().getId());
            // Get the likes count for this tweet
            long likesCountForTweet = likeService.getCountOfLikesForTweet(tweet.getId());
            // Get all the comments for this tweet
            List<CommentDTO> commentDTOS = commentService.getAllCommentsForTweet(tweet.getId());
            // Create the tweet DTO to be sent back
            TweetDTO tweetDTO = new TweetDTO(tweet.getId(), tweet.getTitle(), tweet.getContent(), userDTO.getFirstname() + " " + userDTO.getLastname(), userDTO.getId(), likesCountForTweet, commentDTOS);
            tweetDTOS.add(tweetDTO);
        }

        return tweetDTOS;
    }
}
