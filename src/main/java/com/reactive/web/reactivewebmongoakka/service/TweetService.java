package com.reactive.web.reactivewebmongoakka.service;

import akka.japi.function.Function;
import akka.stream.ActorMaterializer;
import akka.stream.scaladsl.Sink;
import com.reactive.web.reactivewebmongoakka.domain.Hashtag;
import com.reactive.web.reactivewebmongoakka.domain.Tweet;
import com.reactive.web.reactivewebmongoakka.domain.TweetRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import akka.stream.javadsl.Source;
import java.util.HashSet;
import java.util.Set;

@Service
public class TweetService {

    private final TweetRepository repository;
    private final ActorMaterializer actorMaterializer;


    public TweetService(TweetRepository repository, ActorMaterializer actorMaterializer) {
        this.repository = repository;
        this.actorMaterializer = actorMaterializer;
    }

    public Publisher<Tweet> getAllTweets() {
        return this.repository.findAll();
    }


    public Publisher<Hashtag> getAllHastags() {
        return Source.fromPublisher(getAllTweets())
                .map(Tweet::getHashTag)
                .reduce(this::join)
                .mapConcat((Function<Set<Hashtag>, ? extends Iterable<Hashtag>>)  hashtags -> hashtags)
                .runWith(Sink.asPublisher(true), this.actorMaterializer);
    }

    private <T> Set<T> join(Set<T> a, Set<T> b) {
        Set<T> set = new HashSet<>();
        set.addAll(a);
        set.addAll(b);
        return set;
    }
}
