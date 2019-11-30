package SpringMVC.Twitter.Tweets;

import SpringMVC.Twitter.TweetsActions.TweetActions;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tweets")
public class Tweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "tweet", cascade = CascadeType.ALL)
    TweetActions tweetAction;

    private String title, content;

    public Tweet() {}

    public Tweet(String title, String content) {
        this.setTitle(title);
        this.setContent(content);
    }

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

    public void setTweetAction(TweetActions tweetAction) {
        this.tweetAction = tweetAction;
    }
}
