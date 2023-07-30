DROP TABLE IF EXISTS AUTHOR;
create table authors
(
    id         BIGINT AUTO_INCREMENT,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    primary key (id)
);

DROP TABLE IF EXISTS books;
create table books
(
    id    BIGINT AUTO_INCREMENT,
    title varchar(255),
    primary key (id)
);

DROP TABLE IF EXISTS books_authors;
create table books_authors
(
    author_id bigint not null,
    book_id   bigint not null
);

DROP TABLE IF EXISTS categories;
create table categories
(
    id      BIGINT AUTO_INCREMENT,
    book_id bigint,
    name    varchar(255) not null,
    primary key (id)
);

DROP TABLE IF EXISTS comments;
create table comments
(
    id      BIGINT AUTO_INCREMENT,
    book_id bigint not null,
    text    varchar(255),
    primary key (id)
);