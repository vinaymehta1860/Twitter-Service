package SpringMVC.Twitter.tweetService.services;

import SpringMVC.Twitter.tweetService.services.dataFetchers.Comments.CommentsDataFetcher;
import SpringMVC.Twitter.tweetService.services.dataFetchers.Likes.LikesDataFetcher;
import SpringMVC.Twitter.tweetService.services.dataFetchers.Tweets.TweetDataFetcher;
import SpringMVC.Twitter.tweetService.services.dataFetchers.Tweets.TweetsDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {
    @Autowired
    TweetsDataFetcher tweetsDataFetcher;
    @Autowired
    TweetDataFetcher tweetDataFetcher;
    @Autowired
    LikesDataFetcher likesDataFetcher;
    @Autowired
    CommentsDataFetcher commentsDataFetcher;
    @Value("classpath:tweets.graphqls")
    Resource resource;

    private GraphQL graphQL;

    @PostConstruct
    private void loadSchema() throws IOException {
        File file = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("tweets", tweetsDataFetcher)
                        .dataFetcher("tweet", tweetDataFetcher)
                        .dataFetcher("likes", likesDataFetcher)
                        .dataFetcher("comments", commentsDataFetcher))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
