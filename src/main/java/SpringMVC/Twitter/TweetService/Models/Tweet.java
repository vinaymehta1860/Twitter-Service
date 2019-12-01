package SpringMVC.Twitter.TweetService.Models;

import SpringMVC.Twitter.UserService.Models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tweets")
public class Tweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tweet", cascade = CascadeType.ALL)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId
    private long user;

    private String title, content;

    @NotNull
    @Column(name = "date_posted")
    private Date date;

    public Tweet() {}

    public Tweet(String title, String content, long user) {
        this.setTitle(title);
        this.setContent(content);
        this.setDate();
        this.setUser(user);
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

    public void setDate() {
        this.date = new Date();
    }

    public Date getDate() {
        return this.date;
    }

    public void setUser(long user) { this.user = user; }

    public long getUser() { return user; }
}
