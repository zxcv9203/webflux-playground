package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Elapsed {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .elapsed()
                .subscribe(data -> log.info("# onNext : {}, time: {}", data.getT1(), data.getT2()));

        Thread.sleep(6000);
    }
}
