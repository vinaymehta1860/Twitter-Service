package SpringMVC.Twitter.Tweets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class TweetsController {

    @Autowired
    TweetRepository tweetRepository;

    @RequestMapping("/tweets")
    public Iterable<TweetsContent> getAllTweets() {
        return tweetRepository.findAll();
    }

    @RequestMapping("tweets/{id}")
    public Optional<TweetsContent> getTweet(@PathVariable int id) {
        return tweetRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets")
    public boolean addTweet(@RequestBody TweetsContent tweet) {
        TweetsContent t = tweetRepository.save(new TweetsContent(tweet.getTitle(), tweet.getContent()));
        if (t != null)
            return true;
        else
            return false;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tweets/{id}")
    public boolean updateTweet(@RequestBody TweetsContent tweet, @PathVariable int id) {
        Optional<TweetsContent> t = tweetRepository.findById(id);
        if(t.isPresent()) {
            t.get().setTitle(tweet.getTitle());
            t.get().setContent(tweet.getContent());
            tweetRepository.save(t.get());
            return true;
        } else
            return false;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tweets/{id}")
    public void removeTweet(@PathVariable int id) {
        if (tweetRepository.existsById(id))
            tweetRepository.deleteById(id);
    }
}
