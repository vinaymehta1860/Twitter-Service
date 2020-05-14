package SpringMVC.Twitter.tweetService.utility;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Validation {

    public static boolean validateSessionToken(long userId, String sessionToken) {
        // Make the api call to auth service, pass the sessionToken and respond
        //  according to the response
        Request requestBody = new Request(sessionToken, userId);
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.postForObject("", requestBody, Response.class);

        System.out.println("Response: " + response);

        return false;
    }
}
