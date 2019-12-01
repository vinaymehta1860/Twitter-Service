package SpringMVC.Twitter.TweetService.Repositories;

import SpringMVC.Twitter.TweetService.Models.Likes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends CrudRepository<Likes, Long> { }
