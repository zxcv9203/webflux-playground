package org.example.webfluxplayground.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ParallelExample {

    public static void main(String[] args) throws Exception {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext : {}", data));

        Thread.sleep(100L);
    }
}
