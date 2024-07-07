package org.example.webfluxplayground.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class SinksManyUnicastExample {
    public static void main(String[] args) {
        Sinks.Many<Integer> unicastSinks = Sinks.many().unicast()
                .onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSinks.asFlux();

        unicastSinks.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSinks.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        unicastSinks.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

//        fluxView.subscribe(data -> log.info("# Subscriber2 : {}", data));
    }
}
