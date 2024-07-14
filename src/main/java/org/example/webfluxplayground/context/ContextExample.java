package org.example.webfluxplayground.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextExample {

    public static void main(String[] args) throws Exception {
        String key = "company";

        Mono<String> mono = Mono.deferContextual(ctx -> Mono.just("Company: " + " " + ctx.get(key)))
                .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key, "Apple"))
                .subscribe(data -> log.info("# subscribe1 onNext: {}", data));

        mono.contextWrite(context -> context.put(key, "Microsoft"))
                .subscribe(data -> log.info("# subscribe2 onNext: {}", data));

        Thread.sleep(100L);
    }
}
