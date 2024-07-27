package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Next {
    public static void main(String[] args) {
        Flux.fromIterable(() -> "Hello".chars().iterator())
                .next()
                .subscribe(data -> log.info("# onNext : {}", (char)((int)data)));
    }
}
