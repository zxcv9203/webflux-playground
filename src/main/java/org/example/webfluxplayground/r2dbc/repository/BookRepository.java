package org.example.webfluxplayground.r2dbc.repository;

import org.example.webfluxplayground.r2dbc.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Mono<Book> findByIsbn(String isbn);
}
