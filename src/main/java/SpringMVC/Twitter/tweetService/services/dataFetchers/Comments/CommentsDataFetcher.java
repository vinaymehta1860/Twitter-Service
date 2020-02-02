package SpringMVC.Twitter.tweetService.services.dataFetchers.Comments;

import SpringMVC.Twitter.tweetService.DTO.CommentDTO;
import SpringMVC.Twitter.tweetService.services.CommentService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsDataFetcher implements DataFetcher<List<CommentDTO>> {
    @Autowired
    CommentService commentService;

    @Override
    public List<CommentDTO> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return commentService.getAllComments();
    }
}
