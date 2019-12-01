package SpringMVC.Twitter.TweetService.Models;

import javax.persistence.*;

@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @MapsId
    private long tweet;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId
    private long user;

    public Likes() {}

    public Likes(long tweet, long user) {
        this.setTweet(tweet);
        this.setUser(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTweet() {
        return tweet;
    }

    public void setTweet(long tweet) {
        this.tweet = tweet;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
