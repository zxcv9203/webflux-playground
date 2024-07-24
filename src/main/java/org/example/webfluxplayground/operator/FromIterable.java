package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class FromIterable {

    public static void main(String[] args) {
        List<String> itemList = Arrays.asList("item1", "item2", "item3", "item4");

        Flux<String> itemFlux = Flux.fromIterable(itemList);

        itemFlux.subscribe(item -> log.info("Next item: {}", item));
    }
}
