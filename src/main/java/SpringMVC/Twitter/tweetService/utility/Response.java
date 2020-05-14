package SpringMVC.Twitter.tweetService.utility;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private String response;

    public Response() { }

    public Response(String response) {
        this.setResponse(response);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
