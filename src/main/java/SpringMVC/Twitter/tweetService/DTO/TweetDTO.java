package SpringMVC.Twitter.tweetService.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetDTO {
    private long id;
    private String title;
    private String content;
    private String owner;
    private long likes;
    private List<CommentDTO> comments;

    public TweetDTO() {}

    public TweetDTO(long id, String title, String content, String owner, long likes, List<CommentDTO> comments) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setOwner(owner);
        this.setLikes(likes);
        this.setComments(comments);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}