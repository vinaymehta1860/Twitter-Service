package SpringMVC.Twitter.TweetService.Repositories;

import SpringMVC.Twitter.TweetService.Models.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Long> { }
