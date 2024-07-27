package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Map {
    public static void main(String[] args) {
        Flux.just("1-Circle", "3-Circle", "5-Circle")
                .map(circle -> circle.replace("Circle", "Rectangle"))
                .subscribe(data -> log.info(" onNext : {}", data));
    }
}
