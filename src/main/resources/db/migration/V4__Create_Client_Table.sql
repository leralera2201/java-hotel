create table client
(
    id varchar(255) not null
        constraint client_pkey
            primary key,
    description varchar(255),
    email varchar(255),
    name varchar(255),
    number varchar(255),
    patronic varchar(255),
    surname varchar(255)
);

alter table client owner to postgres;

