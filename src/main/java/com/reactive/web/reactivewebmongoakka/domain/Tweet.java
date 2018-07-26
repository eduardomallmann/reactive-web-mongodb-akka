package com.reactive.web.reactivewebmongoakka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    private String id;
    private String text;
    private Author author;

    public Tweet(String text, Author author) {
        this.text = text;
        this.author = author;
    }

    public Set<Hashtag> getHashTag() {
        return Arrays.stream(this.text.split(" "))
                .filter(t -> t.startsWith("#"))
                .map(word -> new Hashtag(word.replaceAll("[^#\\w+]", "").toLowerCase()))
                .collect(Collectors.toSet());
    }

}
