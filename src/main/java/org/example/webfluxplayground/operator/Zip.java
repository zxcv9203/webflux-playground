package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Zip {
    public static void main(String[] args) throws Exception {
        Flux.zip(
                        Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                        Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L))
                )
                .subscribe(data -> log.info("# onNext : {}", data));
        Thread.sleep(2500L);
    }
}
