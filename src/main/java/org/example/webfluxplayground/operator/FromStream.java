package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@Slf4j
public class FromStream {

    public static void main(String[] args) {
        Flux.fromStream(() -> Stream.of("item1", "item2", "item3", "item4"))
                .subscribe(item -> log.info("Next item: {}", item));
    }
}
