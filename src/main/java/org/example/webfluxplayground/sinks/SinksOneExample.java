package org.example.webfluxplayground.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SinksOneExample {
    public static void main(String[] args) throws Exception {
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", FAIL_FAST);
        mono.subscribe(data -> log.info("# Subscriber1 : {}", data));
        mono.subscribe(data -> log.info("# Subscriber2 : {}", data));

    }
}
