package org.example.webfluxplayground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public class WebfluxPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxPlaygroundApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner runWithMvc() {
//        return args -> {
//            LocalDateTime start = LocalDateTime.now();
//            log.info("> mvc 요청 시작 : {}", start);
//            RestClient restClient = RestClient.create();
//
//            for (int i = 0; i < 5; i++) {
//                ResponseEntity<String> response = restClient.get()
//                        .uri("http://localhost:8080/")
//                        .retrieve()
//                        .toEntity(String.class);
//                log.info("응답 받음 : {} {}번째", response.getBody(), i + 1);
//            }
//            LocalDateTime end = LocalDateTime.now();
//            log.info("> mvc 요청 종료 : {}", end);
//        };
//    }
//
//    @Bean
//    public CommandLineRunner runWithWebflux() {
//        return args -> {
//            log.info("> webflux 요청 시작 : {}", LocalDateTime.now());
//            WebClient webClient = WebClient.create();
//
//            for (int i = 0; i < 5; i++) {
//                int finalI = i;
//                webClient.get()
//                        .uri("http://localhost:8080/webflux")
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .subscribe(string -> log.info("응답 받음 : {} {}번째 시간 : {}", string, finalI + 1, LocalDateTime.now()));
//            }
//        };
//    }

}
