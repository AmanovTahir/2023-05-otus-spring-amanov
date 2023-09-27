create table users
(
    id       bigserial    not null,
    login    varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table roles
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);