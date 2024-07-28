package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CollectMap {
    public static void main(String[] args) {
        Flux.range(0, 26)
                .collectMap(key -> (char) (key + 65),
                        value -> String.valueOf((char) (value + 65)))
                .subscribe(map -> log.info("onNext : {}", map));
    }
}
