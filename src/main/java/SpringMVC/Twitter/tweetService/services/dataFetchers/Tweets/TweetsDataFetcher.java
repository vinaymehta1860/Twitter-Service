package SpringMVC.Twitter.tweetService.services.dataFetchers.Tweets;

import SpringMVC.Twitter.tweetService.DTO.TweetDTO;
import SpringMVC.Twitter.tweetService.services.TweetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetsDataFetcher implements DataFetcher<List<TweetDTO>> {
    @Autowired
    TweetService tweetService;

    @Override
    public List<TweetDTO> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return tweetService.findAllTweets();
    }
}
