insert into authors(first_name, last_name)
values ('Михаил', 'Булгаков'),
       ('Николай', 'Гоголь');

insert into categories(name)
values ('классика'),
       ('повесть');


insert into books(author_id, category_id, title)
values (1, 1, 'Мастер и Маргарита'),
       (2, 2, 'Майская ночь, или Утопленница');


