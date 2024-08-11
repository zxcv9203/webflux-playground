package org.example.webfluxplayground.r2dbc.service;

import lombok.RequiredArgsConstructor;
import org.example.webfluxplayground.r2dbc.domain.Book;
import org.example.webfluxplayground.r2dbc.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Mono<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Mono<Book> saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Mono<List<Book>> getAllBooks() {
        return bookRepository.findAll().collectList();
    }

    public Mono<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .flatMap(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setDescription(updatedBook.getDescription());
                    book.setIsbn(updatedBook.getIsbn());
                    return bookRepository.save(book);
                });
    }

    // 책 정보 삭제
    public Mono<Void> deleteBook(Long id) {
        return bookRepository.deleteById(id);
    }
}
