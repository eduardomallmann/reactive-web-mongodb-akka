package com.reactive.web.reactivewebmongoakka.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {
}
