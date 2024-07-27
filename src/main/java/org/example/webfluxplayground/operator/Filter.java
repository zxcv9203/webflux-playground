package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Filter {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .filter(num -> num % 2 == 0)
                .subscribe(data -> log.info("# onNext : {}", data));
    }
}
