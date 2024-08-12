package org.example.webfluxplayground.r2dbc.service;

import lombok.RequiredArgsConstructor;
import org.example.webfluxplayground.r2dbc.domain.Book;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class R2dbcEntityTemplateBookService {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    public Mono<Book> getBookByIsbn(String isbn) {
        return r2dbcEntityTemplate
                .select(Book.class)
                .matching(Query.query(Criteria.where("isbn").is(isbn)))
                .one();
    }

    public Mono<Book> saveBook(Book book) {
        return r2dbcEntityTemplate.insert(Book.class)
                .using(book);
    }

    public Mono<List<Book>> getAllBooks() {
        return r2dbcEntityTemplate
                .select(Book.class)
                .all()
                .collectList();
    }

    public Mono<Book> getBookById(Long id) {
        return r2dbcEntityTemplate
                .select(Book.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }

    public Mono<Book> updateBook(Long id, Book updatedBook) {
        return r2dbcEntityTemplate
                .select(Book.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one()
                .flatMap(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setDescription(updatedBook.getDescription());
                    existingBook.setIsbn(updatedBook.getIsbn());
                    existingBook.setModifiedAt(LocalDateTime.now());
                    return r2dbcEntityTemplate.update(existingBook);
                });
    }

    public Mono<Void> deleteBook(Long id) {
        return r2dbcEntityTemplate
                .delete(Book.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .all()
                .then();
    }
}
