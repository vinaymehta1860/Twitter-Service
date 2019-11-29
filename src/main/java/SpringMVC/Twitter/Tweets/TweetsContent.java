package SpringMVC.Twitter.Tweets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TweetsContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title, content;

    public TweetsContent() {}

    public TweetsContent(String title, String content) {
        this.setTitle(title);
        this.setContent(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
