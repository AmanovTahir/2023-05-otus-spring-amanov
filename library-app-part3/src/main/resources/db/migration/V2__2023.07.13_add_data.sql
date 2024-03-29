INSERT INTO authors (first_name, last_name)
VALUES ('Мария', 'Иванова'),
       ('Алексей', 'Петрович'),
       ('Елена', 'Смирнова'),
       ('Александр', 'Иванов'),
       ('Анна', 'Карпова'),
       ('Ольга', 'Николаева'),
       ('Андрей', 'Васильев'),
       ('Алексей', 'Сидоров'),
       ('Екатерина', 'Федорова'),
       ('Марина', 'Иванова');

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

-- "Звездный Охотник" (Мария Иванова)
INSERT INTO books_authors (author_id, book_id)
VALUES (1, 1);

-- "Путь Меча" (Алексей Петрович)
INSERT INTO books_authors (author_id, book_id)
VALUES (2, 2);

-- "Мгновения вечности" (Елена Смирнова)
INSERT INTO books_authors (author_id, book_id)
VALUES (3, 3);

-- "Тень Империи" (Александр Иванов)
INSERT INTO books_authors (author_id, book_id)
VALUES (4, 4);

-- "Волшебные Тайны" (Анна Карпова)
INSERT INTO books_authors (author_id, book_id)
VALUES (5, 5);

-- "Тайны Потерянного Королевства" (Ольга Николаева)
INSERT INTO books_authors (author_id, book_id)
VALUES (6, 6);

-- "Магия Слов" (Андрей Васильев)
INSERT INTO books_authors (author_id, book_id)
VALUES (7, 7);

-- "Искусство Побеждать" (Алексей Сидоров)
INSERT INTO books_authors (author_id, book_id)
VALUES (8, 8);

-- "Забытое прошлое" (Екатерина Федорова)
INSERT INTO books_authors (author_id, book_id)
VALUES (9, 9);

-- "Сияние Вдохновения" (Марина Иванова)
INSERT INTO books_authors (author_id, book_id)
VALUES (10, 10);


INSERT INTO categories (book_id, name)
VALUES (1, 'Научная фантастика'),
       (2, 'Фэнтези'),
       (3, 'Романтическая драма'),
       (4, 'Хроники о войне'),
       (5, 'Приключенческая фантазия для детей'),
       (6, 'приключения'),
       (7, 'мистика'),
       (8, 'саморазвитие'),
       (9, 'Исторический роман'),
       (10, 'Поэзия'),
       (6, 'Фэнтези'),
       (8, 'Фэнтези'),
       (7, 'Фэнтези');

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