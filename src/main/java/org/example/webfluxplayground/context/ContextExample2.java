package org.example.webfluxplayground.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextExample2 {
    public static void main(String[] args) throws Exception {
        String key1 = "company";
        String key2 = "name";

        Mono.deferContextual(ctx -> Mono.just(ctx.get(key1))
                        .publishOn(Schedulers.parallel())
                        .contextWrite(context -> context.put(key2, "John"))
                        .transformDeferredContextual((mono, context) -> mono.map(data -> data + ", " + context.getOrDefault(key2, "Doe")))
                )
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(100L);
    }
}
