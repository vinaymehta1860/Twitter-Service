package SpringMVC.Twitter.Tweets;

import SpringMVC.Twitter.TweetsActions.TweetActions;
import SpringMVC.Twitter.TweetsActions.TweetActionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class TweetsController {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    TweetActionsRepository tweetActionsRepository;

    @RequestMapping("/tweets")
    public Iterable<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @RequestMapping("tweets/{id}")
    public Optional<Tweet> getTweet(@PathVariable long id) {
        return tweetRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets")
    public boolean addTweet(@RequestBody Tweet tweet) {
        Tweet t = new Tweet(tweet.getTitle(), tweet.getContent());
        TweetActions tweetAction = new TweetActions();

        t.setTweetAction(tweetAction);
        tweetAction.setTweetsContent(t);

        t = tweetRepository.save(t);

        if (t != null)
            return true;
        else
            return false;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tweets/{id}")
    public boolean updateTweet(@RequestBody Tweet tweet, @PathVariable long id) {
        Optional<Tweet> t = tweetRepository.findById(id);
        if(t.isPresent()) {
            t.get().setTitle(tweet.getTitle());
            t.get().setContent(tweet.getContent());
            tweetRepository.save(t.get());
            return true;
        } else
            return false;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/{id}")
    public void removeTweet(@PathVariable long id) {
        if (tweetRepository.existsById(id))
            tweetRepository.deleteById(id);
    }
}
