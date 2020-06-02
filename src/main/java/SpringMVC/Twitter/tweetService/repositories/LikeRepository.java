package SpringMVC.Twitter.tweetService.repositories;

import SpringMVC.Twitter.tweetService.models.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    Like findById(long likeId);

    List<Like> findAllByTweetId(long tweetId);

    Like findByTweetIdAndUserId(long tweetId, String userId);

    long countLikesByTweetId(long tweetId);
}
