create table authors
(
    id         bigserial    not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    primary key (id)
);

create table books
(
    id    bigserial not null,
    title varchar(255),
    primary key (id)
);

create table books_authors
(
    author_id bigint not null,
    book_id   bigint not null
);

create table books_categories
(
    category_id bigint not null,
    book_id     bigint not null
);

create table categories
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);

create table comments
(
    id      bigserial not null,
    book_id bigint    not null,
    text    varchar(255),
    primary key (id)
);

alter table if exists books_authors
    add constraint authors_FK
        foreign key (author_id)
            references authors;

alter table if exists books_authors
    add constraint books_FK
        foreign key (book_id)
            references books;

alter table if exists books_categories
    add constraint category_FK
        foreign key (category_id)
            references categories;

alter table if exists books_categories
    add constraint books_FK
        foreign key (book_id)
            references books;

alter table if exists comments
    add constraint books_FK
        foreign key (book_id)
            references books;