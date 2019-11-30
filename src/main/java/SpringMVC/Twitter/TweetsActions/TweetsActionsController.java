package SpringMVC.Twitter.TweetsActions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TweetsActionsController {

    @Autowired
    TweetActionsRepository tweetActionsRepository;

    @RequestMapping("/tweets/action/like/{id}")
    public String addLike(@PathVariable int id) {
        return "Successfully configured like endpoint.";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets/action/comment/{id}")
    public String addComment(@RequestBody String comment, @PathVariable int id) {
        return "Successfully configured comment endpoint.";
    }
}
