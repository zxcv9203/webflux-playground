package org.example.webfluxplayground.debug;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DebugExample3 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("example.zipWith.checkpoint")
                .map(num -> num + 2)
                .checkpoint("example.map.checkpoint")
                .subscribe(data -> log.info("# onNext: {}", data), error -> log.error("# onError :", error));
    }
}

