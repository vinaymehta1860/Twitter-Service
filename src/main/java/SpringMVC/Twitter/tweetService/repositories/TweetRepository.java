package SpringMVC.Twitter.tweetService.repositories;

import SpringMVC.Twitter.tweetService.models.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
    Tweet findById(long tweetId);

    List<Tweet> findAllByUserId(String id);

    Tweet findAllByUserIdAndId(String userId, long tweetId);
}
