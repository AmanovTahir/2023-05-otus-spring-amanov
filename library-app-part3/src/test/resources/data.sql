INSERT INTO authors (first_name, last_name)
values ('Михаил', 'Булгаков'),
       ('Николай', 'Гоголь');

INSERT INTO books (title)
VALUES ('Мастер и Маргарита'),
       ('Майская ночь, или Утопленница');

INSERT INTO books_authors (author_id, book_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO categories (book_id, name)
VALUES (1, 'классика'),
       (2, 'повесть');

INSERT INTO comments (book_id, text)
VALUES (1, 'comment_1'),
       (1, 'comment_2'),
       (2, 'comment_3'),
       (2, 'comment_4');

