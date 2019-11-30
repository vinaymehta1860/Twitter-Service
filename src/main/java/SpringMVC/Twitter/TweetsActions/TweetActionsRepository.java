package SpringMVC.Twitter.TweetsActions;

import SpringMVC.Twitter.Tweets.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetActionsRepository extends CrudRepository<TweetActions, Long> { }
