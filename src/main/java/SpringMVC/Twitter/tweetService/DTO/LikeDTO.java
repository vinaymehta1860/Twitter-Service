package SpringMVC.Twitter.tweetService.DTO;

import org.springframework.stereotype.Component;

@Component
public class LikeDTO {
    private long id;
    private long tweetId;
    private String userId;
    private String username;

    public LikeDTO() {}

    public LikeDTO(long id, long tweetId, String userId, String username) {
        this.setId(id);
        this.setTweetId(tweetId);
        this.setUserId(userId);
        this.setUsername(username);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
