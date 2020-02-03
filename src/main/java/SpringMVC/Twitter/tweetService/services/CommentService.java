package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.DTO.CommentDTO;
import SpringMVC.Twitter.tweetService.models.Comment;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.repositories.CommentRepository;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TweetService tweetService;
    @Autowired
    UserService userService;

    // Get all comments
    public List<CommentDTO> getAllComments() {
        List<CommentDTO> commentDTOS = new ArrayList<>();

        commentRepository.findAll().forEach(comment -> {
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getComment(), comment.getUser().getFirstname() + " " + comment.getUser().getLastname());
            commentDTOS.add(commentDTO);
        });

        return commentDTOS;
    }

    // Get all comments for a tweet
    public List<CommentDTO> getAllCommentsForTweet(long tweetId) {
        List<CommentDTO> commentDTOS = new ArrayList<>();

        commentRepository.getAllCommentsByTweetId(tweetId).forEach(comment -> {
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getComment(), comment.getUser().getFirstname() + " " + comment.getUser().getLastname());
            commentDTOS.add(commentDTO);
        });

        return commentDTOS;
    }

    // Get all comments for tweets
    public Hashtable<Long, List<CommentDTO>> getAllCommentsForTweets(long[] tweetIds) {
        Hashtable<Long, List<CommentDTO>> comments = new Hashtable<>();

        for (long tweetId : tweetIds) {
            List<CommentDTO> commentDTOS = getAllCommentsForTweet(tweetId);
            comments.put(tweetId, commentDTOS);
        }

        return comments;
    }

    // Add comment
    public Comment addCommentForTweet(Comment commentToAdd, long tweetId, long userId) {
        User user = userService.getUserObjectById(userId);
        Tweet tweet = tweetService.findTweetById(tweetId);

        if (user != null && tweet != null) {
            Comment comment = new Comment(tweet, user, commentToAdd.getComment());
            if (comment != null) {
                commentRepository.save(comment);
                return comment;
            } else
                return null;
        } else {
            return null;
        }
    }

    // Remove comment
    public boolean removeCommentForTweet(long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        }

        return false;
    }

    // Update comment
    public Comment updateCommentForTweet(Comment comment, long commentId) {
        if (commentRepository.existsById(commentId)) {
            Comment commentToUpdate = commentRepository.getCommentById(commentId);

            if (commentToUpdate != null) {
                commentToUpdate.setComment(comment.getComment());
                commentRepository.save(commentToUpdate);
                return commentToUpdate;
            }

            return null;
        }

        return null;
    }
}
