/*
This is a Follower object. It has 3 properties
1) id           <long>    unique identifier of the entry
2) Followee     <User>    User who is being followed
3) Follower     <User>    User who is follows the followee
 */
package SpringMVC.Twitter.tweetService.models;

import SpringMVC.Twitter.userService.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "followers")
public class Follower implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "followee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User followee;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "follower_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User follower;

    public Follower() {}

    public Follower(User followee, User follower) {
        this.setFollowee(followee);
        this.setFollower(follower);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
