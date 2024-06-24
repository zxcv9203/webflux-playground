package org.example.webfluxplayground.reactor.flux;

import reactor.core.publisher.Flux;

public class FluxSimpleExample {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(System.out::println);
    }
}
