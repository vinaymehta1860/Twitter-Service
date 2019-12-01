package SpringMVC.Twitter.TweetService;

import SpringMVC.Twitter.TweetService.Models.Tweet;
import SpringMVC.Twitter.TweetService.Repositories.CommentsRepository;
import SpringMVC.Twitter.TweetService.Repositories.LikesRepository;
import SpringMVC.Twitter.TweetService.Repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class TweetsController {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @RequestMapping("/tweets")
    public List<Tweet> getAllTweets() {
        List<Tweet> list = new ArrayList<>();
        tweetRepository.findAll().forEach(tweet -> list.add(tweet));
        return list;
    }

    @RequestMapping("tweets/{id}")
    public Tweet getTweet(@PathVariable long id) {
        return tweetRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tweets")
    public boolean addTweet(@RequestBody Tweet tweet) {
        Tweet t = new Tweet(tweet.getTitle(), tweet.getContent());
        t = tweetRepository.save(t);

        if (t != null)
            return true;
        else
            return false;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tweets/{id}")
    public boolean updateTweet(@RequestBody Tweet tweet, @PathVariable long id) {
        Tweet t = tweetRepository.findById(id);
        if(t != null) {
            t.setTitle(tweet.getTitle());
            t.setContent(tweet.getContent());
            tweetRepository.save(t);
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
