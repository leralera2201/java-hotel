create table type
(
    id bigint not null
        constraint type_pkey
            primary key,
    description varchar(255),
    name varchar(255)
);

alter table type owner to postgres;

