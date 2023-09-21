create table users
(
    id       bigserial    not null,
    login    varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null,
    primary key (id)
);