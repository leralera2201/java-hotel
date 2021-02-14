create table personnel
(
    id varchar(255) not null
        constraint personnel_pkey
            primary key,
    birthday timestamp,
    description varchar(255),
    email varchar(255),
    name varchar(255),
    number varchar(255),
    patronic varchar(255),
    position varchar(255),
    started_work timestamp,
    surname varchar(255)
);

alter table personnel owner to postgres;

