package org.example.webfluxplayground.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Flux2MonoExample {
    public static void main(String[] args) {
        Flux<String> flux = Mono.justOrEmpty("steve")
                .concatWith(Mono.justOrEmpty("jobs"));

        flux.subscribe(System.out::println);
    }
}
