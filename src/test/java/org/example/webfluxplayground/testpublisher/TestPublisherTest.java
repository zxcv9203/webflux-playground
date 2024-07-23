package org.example.webfluxplayground.testpublisher;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.List;

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

    @Nested
    class MisbehavingTestPublisher {
        public static Flux<Integer> divideByTwo(Flux<Integer> source) {
            return source.zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x / y);
        }
        private static List<Integer> getDataSource() {
            return List.of(2, 4, 6, 8, null);
        }
        @Test
        void divideByTwoTest() {
            TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);

            StepVerifier.create(divideByTwo(source.flux()))
                    .expectSubscription()
                    .then(() -> {
                        getDataSource()
                                .forEach(source::next);
                        source.complete();
                    })
                    .expectNext(1, 2, 3, 4, 5)
                    .expectComplete()
                    .verify();
        }
    }

}
