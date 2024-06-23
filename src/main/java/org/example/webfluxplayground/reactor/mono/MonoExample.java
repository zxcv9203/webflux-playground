package org.example.webfluxplayground.reactor.mono;

import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args) {
        Mono.just("Hello Reactor")
                .subscribe(System.out::println);
    }
}
