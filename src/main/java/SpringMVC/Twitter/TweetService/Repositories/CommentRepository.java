package SpringMVC.Twitter.TweetService.Repositories;

import SpringMVC.Twitter.TweetService.Models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> { }
