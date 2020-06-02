package SpringMVC.Twitter.tweetService.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetDTO {
    private long tweet_id;
    private String title;
    private String content;
    private String owner_name;
    private String owner_id;
    private long likes;
    private List<CommentDTO> comments;

    public TweetDTO() {}

    public TweetDTO(long tweet_id, String title, String content, String owner_name, String owner_id, long likes, List<CommentDTO> comments) {
        this.setTweet_id(tweet_id);
        this.setTitle(title);
        this.setContent(content);
        this.setOwner_name(owner_name);
        this.setOwner_id(owner_id);
        this.setLikes(likes);
        this.setComments(comments);
    }

    public long getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(long tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}