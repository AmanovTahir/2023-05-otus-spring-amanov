INSERT INTO Movies (id, title, release_year)
VALUES (1, 'Звёздные войны: Эпизод IV - Новая надежда', 1977),
       (2, 'Парк Юрского периода', 1993),
       (3, 'Властелин колец: Братство кольца', 2001),
       (4, 'Гарри Поттер и философский камень', 2001),
       (5, 'Аватар', 2009),
       (6, 'Титаник', 1997),
       (7, 'Мстители: Война бесконечности', 2018),
       (8, 'Побег из Шоушенка', 1994),
       (9, 'Пятый элемент', 1997),
       (10, 'В джазе только девушки', 1959);

INSERT INTO Actors (id, firstName, lastName)
VALUES (1, 'Марк', 'Хэмилл'),
       (2, 'Харрисон', 'Форд'),
       (3, 'Кэрри', 'Фишер'),
       (4, 'Сэм', 'Нилл'),
       (5, 'Лора', 'Дерн'),
       (6, 'Джефф', 'Голдблюм'),
       (7, 'Элайджа', 'Вуд'),
       (8, 'Иэн', 'МакКеллен'),
       (9, 'Вигго', 'Мортенсен'),
       (10, 'Дэниел', 'Редклифф'),
       (11, 'Эмма', 'Уотсон'),
       (12, 'Руперт', 'Гринт'),
       (13, 'Сэм', 'Уортингтон'),
       (14, 'Зои', 'Салдана'),
       (15, 'Сигурни', 'Уивер'),
       (16, 'Леонардо', 'ДиКаприо'),
       (17, 'Кейт', 'Уинслет'),
       (18, 'Билли', 'Зейн'),
       (19, 'Роберт', 'Дауни мл.'),
       (20, 'Крис', 'Хемсворт'),
       (21, 'Скарлетт', 'Йоханссон'),
       (22, 'Тим', 'Роббинс'),
       (23, 'Морган', 'Фриман'),
       (24, 'Боб', 'Гантон'),
       (25, 'Брюс', 'Уиллис'),
       (26, 'Милла', 'Йовович'),
       (27, 'Гари', 'Олдман'),
       (28, 'Мэрилин', 'Монро'),
       (29, 'Тони', 'Кёртис'),
       (30, 'Джек', 'Леммон');

INSERT INTO movie_actor (movie_id, actor_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (4, 10),
       (4, 11),
       (4, 12),
       (5, 13),
       (5, 14),
       (5, 15),
       (6, 16),
       (6, 17),
       (6, 18),
       (7, 19),
       (7, 20),
       (7, 21),
       (8, 22),
       (8, 23),
       (8, 24),
       (9, 25),
       (9, 26),
       (9, 27),
       (10, 28),
       (10, 29),
       (10, 30);

INSERT INTO movie_genre (movie_id, genre_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3),
       (5, 1),
       (6, 4),
       (7, 5),
       (8, 4),
       (9, 1),
       (10, 6);

INSERT INTO Genres (id, name)
VALUES (1, 'Фантастика'),
       (2, 'Приключения'),
       (3, 'Фэнтези'),
       (4, 'Драма'),
       (5, 'Супергерои'),
       (6, 'Музыкальная комедия');