package SpringMVC.Twitter.TweetService.Models;

import SpringMVC.Twitter.UserService.Models.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tweets")
public class Tweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private String title, content;

    @NotNull
    @Column(name = "date_created")
    private Date date;

    public Tweet() {}

    public Tweet(String title, String content, User user) {
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

    public void setUser(User user) { this.user = user; }

    public User getUser() { return user; }
}
