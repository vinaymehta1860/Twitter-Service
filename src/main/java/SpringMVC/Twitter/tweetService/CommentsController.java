package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.models.Comment;
import SpringMVC.Twitter.tweetService.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentsController {
    @Autowired
    CommentService commentService;

    // This API is never supposed to be used on the client side. This is just for testing purposes
    @RequestMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // Get all comments for a tweet
    @RequestMapping("/comments/{tweetId}")
    public List<Comment> getAllCommentsForTweet(@PathVariable long tweetId) {
        return commentService.getAllCommentsForTweet(tweetId);
    }

    // Add comment for a tweet
    @RequestMapping(method = RequestMethod.POST, value = "/comments/{tweetId}/{userId}")
    public Comment addCommentForTweet(@RequestBody Comment comment, @PathVariable long tweetId, @PathVariable long userId) {
        return commentService.addCommentForTweet(comment, tweetId, userId);
    }

    // Update comment
    @RequestMapping(method = RequestMethod.PUT, value = "/comments/{commentId}")
    public Comment updateCommentForTweet(@RequestBody Comment comment, @PathVariable long commentId) {
        return commentService.updateCommentForTweet(comment, commentId);
    }

    // Remove comment
    @RequestMapping(method = RequestMethod.DELETE, value = "/comments/{commentId}")
    public boolean removeCommentForTweet(@PathVariable long commentId) {
        return commentService.removeCommentForTweet(commentId);
    }
}
