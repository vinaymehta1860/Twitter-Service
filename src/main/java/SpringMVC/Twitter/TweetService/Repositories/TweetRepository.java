package SpringMVC.Twitter.TweetService.Repositories;

import SpringMVC.Twitter.TweetService.Models.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
    public Tweet findById(long id);
}
