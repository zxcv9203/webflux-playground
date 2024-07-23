package org.example.webfluxplayground.publisherprobe;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class PublisherProbeTest {

    // 평소에는 주전력을 사용해서 작업하다 주전력이 끊겼을 때 예비 전력을 사용해 작업을 진행하는 상황을 시뮬레이션 하기 위한 메서드
    public static Mono<String> processTask(Mono<String> main, Mono<String> standby) {
        return main.flatMap(Mono::just)
                .switchIfEmpty(standby);
    }

    public static Mono<String> supplyMainPower() {
        return Mono.empty();
    }

    public static Mono<String> supplyStandbyPower() {
        return Mono.just("Standby Power");
    }

    @Test
    void publisherProbeTest() {
        PublisherProbe<String> probe = PublisherProbe.of(supplyStandbyPower());

        StepVerifier.create(processTask(supplyMainPower(), probe.mono()))
                .expectNextCount(1)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
