package SpringMVC.Twitter.tweetService.services.dataFetchers.Likes;

import SpringMVC.Twitter.tweetService.models.Like;
import SpringMVC.Twitter.tweetService.services.LikeService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikesDataFetcher implements DataFetcher<List<Like>> {
    @Autowired
    LikeService likeService;

    @Override
    public List<Like> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return likeService.getAllLikes();
    }
}
