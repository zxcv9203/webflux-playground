package org.example.webfluxplayground.sequence.hot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class HotSequenceExample {
    public static void main(String[] args) throws Exception {
        String[] singers = {"Michael Jackson", "Madonna", "Queen"};

        log.info("# Begin concert : ");
        Flux<String> concertFlux = Flux.fromArray(singers)
                .delayElements(Duration.ofSeconds(1))
                .share();

        concertFlux.subscribe(singer -> log.info("Subscriber 1: {}", singer));

        Thread.sleep(2000);

        concertFlux.subscribe(singer -> log.info("Subscriber 2: {}", singer));

        Thread.sleep(3000);
    }
}
