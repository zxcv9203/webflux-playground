CREATE TABLE IF NOT EXISTS BOOK (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    isbn varchar(255) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);