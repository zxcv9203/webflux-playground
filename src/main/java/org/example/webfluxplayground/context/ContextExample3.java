package org.example.webfluxplayground.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextExample3 {

    public static void main(String[] args) throws Exception {
        String key = "company";

        Mono.just("Steve")
//                .transformDeferredContextual(((stringMono, contextView) -> contextView.get("role")))
                .flatMap(name -> Mono.deferContextual(ctx -> Mono.just(ctx.get(key) + ", " + name)
                        .transformDeferredContextual(((stringMono, innerCtx) -> stringMono.map(data -> data + ", " + innerCtx.get("role"))))
                        .contextWrite(context -> context.put("role", "CEO"))))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(100L);
    }
}
