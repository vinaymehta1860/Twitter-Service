package SpringMVC.Twitter.tweetService.repositories;

import SpringMVC.Twitter.tweetService.models.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    long countLikesByTweetId(long tweetId);

    boolean removeLikeByTweetIdAndUserId(long userId, long tweetId);
}
