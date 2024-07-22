package org.example.webfluxplayground.stepverifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.scheduler.VirtualTimeScheduler;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;

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
                    .expectNext(500)
                    .expectComplete()
                    .verify();
        }
    }

    @Nested
    class TimeBased {
        public static Flux<Tuple2<String, Integer>> getCOVID19Count(Flux<Long> source) {
            return source
                    .flatMap(notUse -> Flux.just(
                            Tuples.of("서울", 10),
                            Tuples.of("경기도", 5),
                            Tuples.of("강원도", 3),
                            Tuples.of("충청도", 6),
                            Tuples.of("경상도", 5),
                            Tuples.of("전라도", 8),
                            Tuples.of("인천", 2),
                            Tuples.of("대전", 1),
                            Tuples.of("대구", 2),
                            Tuples.of("부산", 3),
                            Tuples.of("제주도", 0)
                    ));
        }

        public static Flux<Tuple2<String, Integer>> getVoteCount(Flux<Long> source) {
            return source
                    .zipWith(Flux.just(
                            Tuples.of("중구", 15400),
                            Tuples.of("서초구", 20020),
                            Tuples.of("강서구", 32040),
                            Tuples.of("강동구", 14506),
                            Tuples.of("서대문구", 35650)
                    ))
                    .map(Tuple2::getT2);
        }

        @Test
        @DisplayName("타임 기반 테스트 1")
        void test1() {
            StepVerifier.withVirtualTime(() -> getCOVID19Count(Flux.interval(Duration.ofHours(1)).take(1)))
                    .expectSubscription()
                    .then(() -> VirtualTimeScheduler
                            .get()
                            .advanceTimeBy(Duration.ofHours(1)))
                    .expectNextCount(11)
                    .expectComplete()
                    .verify();
        }

        @Test
        @DisplayName("타임 기반 테스트 2")
        void test2() {
            StepVerifier.create(getCOVID19Count(Flux.interval(Duration.ofMinutes(1)).take(1)))
                    .expectSubscription()
                    .expectNextCount(11)
                    .expectComplete()
                    .verify(Duration.ofSeconds(3));
        }

        @Test
        @DisplayName("타임 기반 테스트 3")
        void test3() {
            StepVerifier.withVirtualTime(() -> getVoteCount(Flux.interval(Duration.ofMinutes(1))))
                    .expectSubscription()
                    .expectNoEvent(Duration.ofMinutes(1))
                    .expectNoEvent(Duration.ofMinutes(1))
                    .expectNoEvent(Duration.ofMinutes(1))
                    .expectNoEvent(Duration.ofMinutes(1))
                    .expectNoEvent(Duration.ofMinutes(1))
                    .expectNextCount(5)
                    .expectComplete()
                    .verify();
        }
    }

    @Nested
    class BackPressure {
        public static Flux<Integer> generateNumber() {
            return Flux.create(emitter -> {
                for (int i = 1; i <= 100; i++) {
                    emitter.next(i);
                }
                emitter.complete();
            }, FluxSink.OverflowStrategy.ERROR);
        }

        @Test
        @DisplayName("[실패] 오버플로우가 발생하여 실패")
        void generateNumberTest() {
            StepVerifier.create(generateNumber(), 1L)
                    .thenConsumeWhile(num -> num >= 1)
                    .verifyComplete();
        }

        @Test
        @DisplayName("[성공] 에러 발생을 기대하고 Drop된 데이터가 있음을 검증")
        void generateNumberTest2() {
            StepVerifier.create(generateNumber(), 1L)
                    .thenConsumeWhile(num -> num >= 1)
                    .expectError()
                    .verifyThenAssertThat()
                    .hasDroppedElements();
        }
    }
}
