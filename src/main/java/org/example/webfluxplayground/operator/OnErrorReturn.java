package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorReturn {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5)
                .<Integer>handle((num, sink) -> {
                    if (num == 3) {
                        sink.error(new IllegalArgumentException("Not allowed 3"));
                        return;
                    }
                    sink.next(num * 2);
                })
                .onErrorReturn(-1)
                .subscribe(data -> log.info("# onNext : {}", data),
                        error -> log.error("# onError : ", error));
    }
}
