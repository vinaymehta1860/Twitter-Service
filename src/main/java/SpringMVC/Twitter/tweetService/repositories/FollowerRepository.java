package SpringMVC.Twitter.tweetService.repositories;

import SpringMVC.Twitter.tweetService.models.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Long> {

    List<Follower> findAllByFolloweeId(String followeeId);

    List<Follower> findAllByFollowerId(String followerId);

    Follower findByFolloweeIdAndFollowerId(String followeeId, String followerId);

    boolean existsByFolloweeIdAndFollowerId(String followeeId, String followerId);
}
