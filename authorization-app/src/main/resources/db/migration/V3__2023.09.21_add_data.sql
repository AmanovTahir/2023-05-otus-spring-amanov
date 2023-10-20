INSERT INTO authors (first_name, last_name)
VALUES ('Мария', 'Иванова'),
       ('Алексей', 'Петрович'),
       ('Елена', 'Смирнова'),
       ('Александр', 'Иванов'),
       ('Анна', 'Карпова'),
       ('Ольга', 'Николаева'),
       ('Андрей', 'Васильев'),
       ('Алексей', 'Сидоров'),
       ('Екатерина', 'Федорова');

INSERT INTO books (title)
VALUES ('Звездный Охотник'),
       ('Путь Меча'),
       ('Мгновения вечности'),
       ('Тень Империи'),
       ('Волшебные Тайны'),
       ('Тайны Потерянного Королевства'),
       ('Магия Слов'),
       ('Искусство Побеждать'),
       ('Забытое прошлое'),
       ('Сияние Вдохновения');

INSERT INTO categories (name)
VALUES ('Научная фантастика'),
       ('Фэнтези'),
       ('Романтическая драма'),
       ('Хроники о войне'),
       ('Приключенческая фантазия для детей'),
       ('приключения'),
       ('мистика'),
       ('саморазвитие'),
       ('Исторический роман'),
       ('Поэзия');

INSERT INTO books_authors (author_id, book_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (1, 10);

INSERT INTO books_categories (category_id, book_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (1, 10);


INSERT INTO comments (book_id, text)
VALUES (1, 'Увлекательная научная фантастика, читал на одном дыхании!'),
       (1, 'Персонажи такие живые, что кажется, будто сам в космосе путешествуешь!'),
       (2, 'Очарован миром фэнтези от Алексея Петровича!'),
       (2, 'Эпические сражения и герои, которые остаются в памяти навсегда!'),
       (3, 'Какое трогательное произведение, просто слов нет!'),
       (3, 'С трудом удержал слезы, читая это прекрасное произведение.'),
       (4, 'Фантастическая военная история, которая завладела моим сердцем.'),
       (4, 'Александр Иванов - мастер создания напряженных моментов и сюжетных поворотов!'),
       (5, 'Моя малышка просто обожает эту книжку и просит читать её снова и снова!'),
       (5, 'Книга, которая запоминается на всю жизнь!'),
       (6, 'Ольга Николаева - настоящий мастер по созданию удивительных миров!'),
       (6, 'Прекрасное сочетание фэнтези и загадок!'),
       (7, 'Для ценителей мистической атмосферы и глубоких переживаний.'),
       (7, 'Книга, которая заставляет задуматься о смысле слов и их силе!'),
       (8, 'Превосходное пособие для тех, кто хочет стать настоящим лидером!'),
       (8, 'Мотивирующая книга, которая поможет преодолеть любые препятствия.'),
       (9, 'Восхитительная историческая драма, которая уносит во времена давно минувшие.'),
       (9, 'Екатерина Федорова - гений жанра!'),
       (10, 'Прекрасные стихи, наполненные глубоким смыслом и красотой!'),
       (10, 'Марина Иванова - поэт с большой буквы, её стихи умиротворяют и вдохновляют.');


INSERT INTO users (login, password)
values ('admin', '1'),
       ('user', '1');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);