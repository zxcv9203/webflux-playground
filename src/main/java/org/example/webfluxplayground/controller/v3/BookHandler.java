package org.example.webfluxplayground.controller.v3;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {
    public Mono<ServerResponse> createBook(ServerRequest request) {
        return Mono.just("Book created")
                .flatMap(data -> ServerResponse.ok().bodyValue(data));
    }

}
