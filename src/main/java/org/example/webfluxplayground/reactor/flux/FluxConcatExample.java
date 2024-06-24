package org.example.webfluxplayground.reactor.flux;

import reactor.core.publisher.Flux;

public class FluxConcatExample {
    public static void main(String[] args) {
        Flux.concat(
                        Flux.just("Mercury", "Venus", "Earth"),
                        Flux.just("Mars", "Jupiter", "Saturn"),
                        Flux.just("Uranus", "Neptune", "Pluto"))
                .collectList()
                .subscribe(System.out::println);
    }
}
