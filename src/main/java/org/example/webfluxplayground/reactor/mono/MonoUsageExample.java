package org.example.webfluxplayground.reactor.mono;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CountDownLatch;

public class MonoUsageExample {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        CountDownLatch latch = new CountDownLatch(1);

        WebClient restClient = WebClient.create();
        restClient.get()
                .uri("http://worldtimeapi.org/api/timezone/Asia/Seoul")
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        return objectMapper.readTree(response).get("datetime").asText();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .subscribe(
                        data -> System.out.println("# emitted data : " + data),
                        error -> {
                            System.out.println("# emitted error signal");
                            latch.countDown();
                        },
                        () -> {
                            System.out.println("# emitted onComplete signal");
                            latch.countDown();
                        }
                );
        latch.await();
    }
}
