package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorResume {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.just(1, 2, 3, 4)
                .concatWith(Flux.error(new RuntimeException("An error occurred!")))
                .concatWith(Flux.just(5));

        Flux<Integer> fallback = Flux.just(10, 20, 30);

        source
                .onErrorResume(e -> {
                    System.out.println("Caught error: " + e.getMessage());
                    return fallback;
                })
                .subscribe(
                        data -> System.out.println("Next: " + data),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Completed")
                );

    }
}
