package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Range {
    public static void main(String[] args) {
        Flux.range(5, 10)
                .subscribe(data -> log.info("Next item: {}", data));
    }
}
