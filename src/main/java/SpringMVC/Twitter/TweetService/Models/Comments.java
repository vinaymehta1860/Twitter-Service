package SpringMVC.Twitter.TweetService.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Comments {
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

    @NotNull
    private String comment;

    public Comments() {}

    public Comments(long tweet, long user, String comment) {
        this.setTweet(tweet);
        this.setUser(user);
        this.setComment(comment);
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
