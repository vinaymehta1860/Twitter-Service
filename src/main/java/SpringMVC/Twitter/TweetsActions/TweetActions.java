package SpringMVC.Twitter.TweetsActions;

import SpringMVC.Twitter.Tweets.Tweet;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class TweetActions implements Serializable {
    // Columns
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long likes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    @MapsId
    private Tweet tweet;

    // Constructors
    public TweetActions() {
        this.likes = 0;
    }

    public TweetActions (int n) {
        this.likes = (long) n;
    }

    // Getters and setters
    public long getLikes() {
        return likes;
    }

    public void setTweetsContent(Tweet tweet) {
        this.tweet = tweet;
    }
}
