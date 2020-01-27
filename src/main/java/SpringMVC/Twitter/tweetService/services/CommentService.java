package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.models.Comment;
import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.repositories.CommentRepository;
import SpringMVC.Twitter.userService.UserService;
import SpringMVC.Twitter.userService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach((comment) -> comments.add(comment));
        return comments;
    }

    // Get all comments for a tweet
    public List<Comment> getAllCommentsForTweet(long tweetId) {
        return commentRepository.getAllCommentsByTweetId(tweetId);
    }

    // Add comment
    public Comment addCommentForTweet(Comment commentToAdd, long tweetId, long userId) {
        User user = userService.findUserById(userId);
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
