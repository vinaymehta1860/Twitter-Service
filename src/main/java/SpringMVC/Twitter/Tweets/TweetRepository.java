package SpringMVC.Twitter.Tweets;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<TweetsContent, Integer> {
}
