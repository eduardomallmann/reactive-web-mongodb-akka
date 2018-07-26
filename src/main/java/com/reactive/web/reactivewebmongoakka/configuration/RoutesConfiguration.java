package com.reactive.web.reactivewebmongoakka.configuration;

import com.reactive.web.reactivewebmongoakka.domain.Hashtag;
import com.reactive.web.reactivewebmongoakka.domain.Tweet;
import com.reactive.web.reactivewebmongoakka.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutesConfiguration {

    private final TweetService tweetService;

    public RoutesConfiguration(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/tweets"), request -> ok().body(tweetService.getAllTweets(), Tweet.class))
                .andRoute(GET("/hashtags"), request -> ok().body(tweetService.getAllHastags(), Hashtag.class));
    }


}
