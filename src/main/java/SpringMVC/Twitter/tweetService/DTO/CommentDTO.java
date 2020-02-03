package SpringMVC.Twitter.tweetService.DTO;

import org.springframework.stereotype.Component;

@Component
public class CommentDTO {
    private long id;
    private String comment;
    private String owner;

    public CommentDTO() {}

    public CommentDTO(long id, String comment, String owner) {
        this.setId(id);
        this.setComment(comment);
        this.setOwner(owner);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}