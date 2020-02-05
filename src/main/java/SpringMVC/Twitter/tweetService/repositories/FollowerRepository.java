package SpringMVC.Twitter.tweetService.repositories;

import SpringMVC.Twitter.tweetService.models.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Long> {

    List<Follower> findAllByFolloweeId(long followeeId);

    Follower findByFolloweeIdAndFollowerId(long followeeId, long followerId);

    boolean existsByFolloweeIdAndFollowerId(long followeeId, long followerId);
}
