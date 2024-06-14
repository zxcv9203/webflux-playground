package org.example.webfluxplayground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
public class SpringMvcController {
    private final RestClient restClient = RestClient.create();

    @GetMapping
    public ResponseEntity<String> get() {
        ResponseEntity<String> response = restClient.get()
                .uri("http://localhost:8080/external-api")
                .retrieve()
                .toEntity(String.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getBody());
    }

    @GetMapping("/external-api")
    public ResponseEntity<String> getExternalApi() throws InterruptedException {
        Thread.sleep(5000);
        return ResponseEntity.ok("Hello from external API");
    }

}
