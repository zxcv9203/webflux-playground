package org.example.webfluxplayground.testpublisher;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTest {

    @Nested
    class WellBehavedTestPublisher {
        public static Flux<Integer> divideByTwo(Flux<Integer> source) {
            return source.zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x / y);
        }
        @Test
        void divideByTwoTest() {
            TestPublisher<Integer> source = TestPublisher.create();

            StepVerifier.create(divideByTwo(source.flux()))
                    .expectSubscription()
                    .then(() -> source.emit(2, 4, 6, 8, 10))
                    .expectNext(1, 2, 3, 4)
                    .expectError()
                    .verify();
        }
    }

}
