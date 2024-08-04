package org.example.webfluxplayground.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webflux")
public class WebFluxController {

    private final WebClient webClient = WebClient.create();

    @GetMapping
    public Mono<String> get() {
        return webClient.get()
                .uri("http://localhost:8080/webflux/external-api")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/external-api")
    public Mono<String> getExternalApi() throws InterruptedException {
        Thread.sleep(5000);
        return Mono.just("Hello from external API");
    }
}
