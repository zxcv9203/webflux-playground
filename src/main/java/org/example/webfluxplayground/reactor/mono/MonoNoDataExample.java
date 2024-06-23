package org.example.webfluxplayground.reactor.mono;

import reactor.core.publisher.Mono;

public class MonoNoDataExample {
    public static void main(String[] args) {
        Mono.empty()
                .subscribe(
                        none -> System.out.println("# emitted onNext siganl"),
                        error -> {
                        },
                        () -> System.out.println("# emitted onComplete signal")
                );
    }
}
