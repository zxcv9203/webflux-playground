package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FlatMap {
    public static void main(String[] args) {
        Flux.just("Good", "Bad")
                .flatMap(feeling -> Flux.just("Morning", "Afternoon", "Evening")
                        .map(time -> feeling + " " + time))
                .subscribe(log::info);
    }
}
