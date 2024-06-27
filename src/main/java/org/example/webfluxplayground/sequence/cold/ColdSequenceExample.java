package org.example.webfluxplayground.sequence.cold;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class ColdSequenceExample {
    public static void main(String[] args) {
        Flux<String> coldFlux = Flux.fromIterable(List.of("KOREA", "JAPAN", "CHINA"))
                .map(String::toLowerCase);

        coldFlux.subscribe(country -> log.info("Subscriber 1: {}", country));
        System.out.println("----------------------------------------------");
        coldFlux.subscribe(country -> log.info("Subscriber 2: {}", country));
    }
}
