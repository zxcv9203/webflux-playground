package org.example.webfluxplayground.backpressure;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackpressureDropStrategy {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop(dropped -> log.info("# dropped: {}", dropped))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException ignore) {
                    }
                    log.info("# onNext : {}", data);
                },
                        error -> log.error("# onError"));
        Thread.sleep(2000L);
    }
}
