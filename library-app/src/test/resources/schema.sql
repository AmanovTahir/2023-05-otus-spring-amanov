CREATE TABLE categories
(
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL
);

create table authors
(
    author_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL
);

CREATE TABLE books
(
    book_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_id   BIGINT,
    category_id BIGINT,
    title       VARCHAR(250) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (author_id) ON DELETE NO ACTION,
    FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE NO ACTION
);
