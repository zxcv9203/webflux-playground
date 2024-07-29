package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class BufferTimeout {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .map(num -> {
                    try {
                        if (num < 10) {
                            Thread.sleep(100);
                        } else {
                            Thread.sleep(300);
                        }
                    } catch (InterruptedException ignored) {
                    }
                    return num;
                })
                .bufferTimeout(3, Duration.ofMillis(400))
                .subscribe(buffer -> log.info("# onNext : {}", buffer));
    }
}
