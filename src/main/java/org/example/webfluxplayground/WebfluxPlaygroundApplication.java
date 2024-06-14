package org.example.webfluxplayground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class WebfluxPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxPlaygroundApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            LocalDateTime start = LocalDateTime.now();
            log.info("> 요청 시작 : {}", start);
            RestClient restClient = RestClient.create();

            for (int i = 0; i < 5; i++) {
                ResponseEntity<String> response = restClient.get()
                        .uri("http://localhost:8080/external-api")
                        .retrieve()
                        .toEntity(String.class);
                log.info("응답 받음 : {} {}번째", response.getBody(), i + 1);
            }
            LocalDateTime end = LocalDateTime.now();
            log.info("> 요청 종료 : {}", end);
            log.info("총 걸린 시간 : {}초", (end.getSecond() - start.getSecond()));
        };
    }

}
