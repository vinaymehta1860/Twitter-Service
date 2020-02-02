package SpringMVC.Twitter.tweetService.services.dataFetchers.Tweets;

import SpringMVC.Twitter.tweetService.models.Tweet;
import SpringMVC.Twitter.tweetService.services.TweetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TweetDataFetcher implements DataFetcher<Tweet> {
    @Autowired
    TweetService tweetService;

    @Override
    public Tweet get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = dataFetchingEnvironment.getArgument("id");
        return tweetService.findTweetById(id);
    }
}
