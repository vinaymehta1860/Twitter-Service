package SpringMVC.Twitter.tweetService.dtos.tweets;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class TweetsDTO {
    @Id
    @NotNull
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
