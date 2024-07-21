package org.example.webfluxplayground.stepverifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class StepVerifierTest {

    @Nested
    class SignalEvent {

        public static Flux<String> sayHello() {
            return Flux.just("Hello", "Reactor");
        }

        public static Flux<Integer> divideByTwo(Flux<Integer> source) {
            return source.zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x / y);
        }

        public static Flux<Integer> takeNumber(Flux<Integer> source, long n) {
            return source.take(n);
        }

        @Test
        @DisplayName("시그널 테스트 1")
        void test1() {
            StepVerifier.create(Mono.just("Hello Reactor")) // 테스트 대상 Sequence 생성
                    .expectNext("Hello Reactor") // emit된 데이터 기댓값 평가
                    .expectComplete() // onComplete Signal 기댓값 평가
                    .verify(); // 검증 실행
        }

        @Test
        @DisplayName("시그널 테스트 2")
        void test2() {
            StepVerifier.create(sayHello())
                    .expectSubscription()
                    .as("# expect subscription")
                    .expectNext("Hi") // Hi가 아닌 Hello가 emit되므로 테스트 실패
                    .as("# expect Hi")
                    .expectNext("Reactor")
                    .as("# expect Reactor")
                    .verifyComplete();
        }

        @Test
        @DisplayName("시그널 테스트 3")
        void test3() {
            Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);

            StepVerifier.create(divideByTwo(source))
                    .expectNext(1)
                    .expectNext(2)
                    .expectNext(3)
                    .expectNext(4)
                    .expectError(ArithmeticException.class)
                    .verify();
        }

        @Test
        @DisplayName("시그널 테스트 4")
        void test4() {
            Flux<Integer> source = Flux.range(0, 1000);
            StepVerifier.create(takeNumber(source, 500), StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                    .expectSubscription()
                    .expectNext(0)
                    .expectNextCount(498)
                    .expectNext(499)
                    .expectComplete()
                    .verify();
        }
    }
}
