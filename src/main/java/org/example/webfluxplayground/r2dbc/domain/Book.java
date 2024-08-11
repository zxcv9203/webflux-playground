package org.example.webfluxplayground.r2dbc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    private Long id;

    private String title;
    private String author;
    private String description;
    private String isbn;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("last_modified_at")
    private LocalDateTime modifiedAt;
}
