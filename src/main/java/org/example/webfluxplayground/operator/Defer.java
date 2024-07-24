package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public class Defer {
    public static void main(String[] args) throws InterruptedException {
        log.info("# start : {}", LocalDateTime.now());
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# justMono1 : {}", data));
        deferMono.subscribe(data -> log.info("# deferMono1 : {}", data));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# justMono2 : {}", data));
        deferMono.subscribe(data -> log.info("# deferMono2 : {}", data));
    }
}
