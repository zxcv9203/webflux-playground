package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;

@Slf4j
public class GroupBy {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("apple", "banana", "orange", "apple", "banana", "grape");

        Flux<GroupedFlux<String, String>> groupedFlux = flux.groupBy(fruit -> fruit);

        groupedFlux.concatMap(group -> group.collectList()
                        .map(list -> group.key() + ": " + list))
                .subscribe(log::info);
    }
}
