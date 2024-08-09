package org.example.webfluxplayground.controller.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> routeBook(BookHandler handler) {
        return route()
                .POST("/v1/books", handler::createBook)
                .build();
    }
}
