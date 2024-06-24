package org.example.webfluxplayground.reactor.flux;

import reactor.core.publisher.Flux;

public class FluxFromArrayExample {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{6, 9, 13})
                .filter(num -> num > 6)
                .map(num -> num % 2)
                .subscribe(System.out::println);
    }
}
