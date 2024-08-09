package org.example.webfluxplayground.controller.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webfluxplayground.controller.v3.validator.BookCustomValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookHandler {

    private final BookCustomValidator bookCustomValidator;
    public Mono<ServerResponse> createBook(ServerRequest request) {
        return Mono.just("Book created")
                .doOnNext(this::validate)
                .flatMap(data -> ServerResponse.ok().bodyValue(data));
    }

    private void validate(String book) {
        Errors errors = new BeanPropertyBindingResult(book, book);

        bookCustomValidator.validate(book, errors);
        if (errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
            throw new ServerWebInputException(errors.toString());
        }
    }
}
