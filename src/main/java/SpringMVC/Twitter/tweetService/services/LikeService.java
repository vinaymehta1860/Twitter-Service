package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.DTO.LikeDTO;
import SpringMVC.Twitter.tweetService.repositories.LikeRepository;
import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.userService.DTO.UserDTO;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
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

    // Get all likes
    public List<Like> getAllLikes() {
        List<Like> likes = new ArrayList<>();
        likeRepository.findAll().forEach(like -> likes.add(like));
        return likes;
    }

    // Get all the users who have liked a tweet
    public List<UserDTO> getUsersWhoHaveLikedATweet(long tweetId) {
        List<UserDTO> userDTOS = new ArrayList<>();

        likeRepository.findAllByTweetId(tweetId).forEach(like -> {
            userDTOS.add(convertUserObjectToUserDTOs(Arrays.asList(like.getUser())).get(0));
        });

        return userDTOS;
    }

    // Get the count of likes for a tweet
    public long getCountOfLikesForTweet(long tweetId) {
        return likeRepository.countLikesByTweetId(tweetId);
    }

    // Get number of likes for list of tweets
    public Hashtable<Long, Long> getLikesCountForTweets(long[] tweetIds) {
        Hashtable<Long, Long> likes = new Hashtable<>();

        for (long tweetId : tweetIds) {
            long likesCountForTweet = getCountOfLikesForTweet(tweetId);
            likes.put(tweetId, likesCountForTweet);
        }

        return likes;
    }

    // Add like for a tweet
    public boolean registerLikeForTweetByUser(long tweetId, String userId) {
        User user = userService.getUserObjectById(userId);
        Tweet tweet = tweetService.getTweetObjectById(tweetId);

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
    public boolean deleteLikeForTweetByUser(long tweetId, String userId) {
        try {
            long likeId = likeRepository.findByTweetIdAndUserId(tweetId, userId).getId();
            likeRepository.deleteById(likeId);
            return true;
        } catch(Exception e) {
            System.out.println("Exception: " + e.toString());
            return false;
        }
    }

    /*
     * Private Utility methods
     */

    // Get a like by its id
    private LikeDTO getLikeById(long likeId) {
        Like like = likeRepository.findById(likeId);

        return new LikeDTO(like.getId(), like.getTweet().getId(), like.getUser().getId(), like.getUser().getFirstname() + " " + like.getUser().getLastname());
    }

    // Convert user entity objects to user DTOs
    private List<UserDTO> convertUserObjectToUserDTOs(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    // Function to convert a list of Like Entity objects to List of LikeDTO
    private List<LikeDTO> convertLikeObjectsToLikeDTOs(List<Like> likes) {
        List<LikeDTO> likeDTOS = new ArrayList<>();

        for (Like like : likes) {
            LikeDTO likeDTO = new LikeDTO(like.getId(), like.getTweet().getId(), like.getUser().getId(), like.getUser().getFirstname() + " " + like.getUser().getLastname());
            likeDTOS.add(likeDTO);
        }

        return likeDTOS;
    }
}
