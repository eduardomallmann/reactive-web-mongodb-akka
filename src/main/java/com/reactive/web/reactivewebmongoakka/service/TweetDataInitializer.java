package com.reactive.web.reactivewebmongoakka.service;

import com.reactive.web.reactivewebmongoakka.domain.Author;
import com.reactive.web.reactivewebmongoakka.domain.Tweet;
import com.reactive.web.reactivewebmongoakka.domain.TweetRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TweetDataInitializer implements ApplicationRunner {

    private final TweetRepository tweetRepository;

    public TweetDataInitializer(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Author jonas = new Author("jboner"),
                viktor = new Author("viktorklang"),
                josh = new Author("starbuxman");

        Flux<Tweet> tweetFlux = Flux.just(
                new Tweet("Woot, Konrad will be talking about #Enterprise #Integration done right! #akka #alpakka",
                        viktor),
                new Tweet("#scala implicits can easily be used to model capabilities, but can they encode " +
                        "obligations easily? Easily as in: ergonomcally?", viktor),
                new Tweet("This is so cool! #akka", viktor),
                new Tweet("Cross data center replication of event sourrced #akka actors is soon available (using " +
                        "#CRDTs and more)", jonas),
                new Tweet("a reminder: @SpingBoot lets you pair-program with the #spring team", josh),
                new Tweet("whatever you next #platform is, don't built it yourself. Even companies with the $$ and " +
                        "motivation to do it fail. A LOT", josh)
        );

        tweetRepository
                .deleteAll()
                .thenMany(tweetRepository.saveAll(tweetFlux))
                .thenMany(tweetRepository.findAll())
                .subscribe(System.out::println);

    }
}
