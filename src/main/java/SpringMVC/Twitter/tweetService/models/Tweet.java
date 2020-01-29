/*
This is a Tweet object. It has 4 properties
1) id       <long>      unique identifier of tweet
2) title    <String>    title of tweet
3) content  <String>    content of the tweet
4) date     <Date>      date when tweet was created
 */
package SpringMVC.Twitter.tweetService.models;

import SpringMVC.Twitter.userService.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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
