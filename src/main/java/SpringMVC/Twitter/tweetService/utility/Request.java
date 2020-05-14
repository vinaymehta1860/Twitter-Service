package SpringMVC.Twitter.tweetService.utility;

import org.springframework.stereotype.Component;

@Component
public class Request {
    private String sessionToken;
    private long userId;

    public Request() {}

    public Request(String sessionToken, long userId) {
        this.setSessionToken(sessionToken);
        this.setUserId(userId);
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
