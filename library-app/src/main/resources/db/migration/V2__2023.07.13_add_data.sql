insert into authors(first_name, last_name)
values ('Михаил', 'Булгаков'),
       ('Николай', 'Гоголь'),
       ('Александр', 'Пушкин'),
       ('Федор', 'Достоевский'),
       ('Джек', 'Лондон'),
       ('Марк', 'Твен');

insert into categories(name)
values ('комедия'),
       ('роман'),
       ('трагедия'),
       ('драма'),
       ('сатира'),
       ('фентази'),
       ('классика'),
       ('детектив'),
       ('повесть'),
       ('поэма');

insert into books(author_id, category_id, title)
values (1, 7, 'Мастер и Маргарита'),
       (2, 9, 'Майская ночь, или Утопленница'),
       (3, 10, 'Медный всадник'),
       (4, 2, 'Униженные и оскорблённые'),
       (5, 4, 'Мартин Иден'),
       (6, 5, 'Янки из Коннектикута при дворе короля Артура')