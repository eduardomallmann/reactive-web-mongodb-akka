package com.reactive.web.reactivewebmongoakka.configuration;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfiguration {

    @Bean
    ActorSystem actorSystem() {
        return ActorSystem.create("bootiful-akka-stream");
    }

    @Bean
    ActorMaterializer actorMaterializer() {
        return ActorMaterializer.create(this.actorSystem());
    }
}
