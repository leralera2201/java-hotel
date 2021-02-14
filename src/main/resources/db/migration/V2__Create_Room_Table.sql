create table room
(
    id bigint not null
        constraint room_pkey
            primary key,
    description varchar(255),
    name varchar(255),
    places_count integer,
    price numeric(19,2),
    type_id bigint
        constraint fk5j64f1pymatm2vu9kk67spg65
            references type
);

alter table room owner to postgres;

