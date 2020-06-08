package SpringMVC.Twitter.tweetService;

import SpringMVC.Twitter.tweetService.DTO.CommentDTO;
import SpringMVC.Twitter.tweetService.models.Comment;
import SpringMVC.Twitter.tweetService.services.CommentService;
import SpringMVC.Twitter.tweetService.utility.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    @Autowired
    CommentService commentService;
    @Autowired
    JWTUtil jwtUtil;

    // This API is never supposed to be used on the client side. This is just for testing purposes
    @RequestMapping("/comments")
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments();
    }

    // Get all comments for a tweet
    @RequestMapping("/users/{userId}/tweets/{tweetId}/comments")
    public List<CommentDTO> getAllCommentsForTweet(@PathVariable String userId, @PathVariable long tweetId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return commentService.getAllCommentsForTweet(tweetId);
        } else {
            return null;
        }
    }

    // Add comment for a tweet
    @RequestMapping(method = RequestMethod.POST, value = "users/{userId}/tweets/{tweetId}/comments")
    public Comment addCommentForTweet(@PathVariable String userId, @PathVariable long tweetId, @RequestBody Comment comment, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return commentService.addCommentForTweet(comment, tweetId, userId);
        } else {
            return null;
        }
    }

    // Update comment
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/tweets/comments/{commentId}")
    public Comment updateCommentForTweet(@PathVariable String userId, @PathVariable long commentId, @RequestBody Comment comment, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return commentService.updateCommentForTweet(comment, commentId);
        } else {
            return null;
        }
    }

    // Remove comment
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/comments/{commentId}")
    public boolean removeCommentForTweet(@PathVariable String userId, @PathVariable long commentId, @RequestHeader("Authorization") String access_token) {
        if (jwtUtil.isAccessTokenValid(access_token, userId)) {
            return commentService.removeCommentForTweet(commentId);
        } else {
            return false;
        }
    }
}
