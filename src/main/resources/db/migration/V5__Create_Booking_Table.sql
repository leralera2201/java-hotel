create table booking
(
    id varchar(255) not null
        constraint booking_pkey
            primary key,
    arrival_date timestamp,
    departure_date timestamp,
    description varchar(255),
    client_id varchar(255)
        constraint fkhs7eej4m2orrmr5cfbcrqs8yw
            references client,
    room_id bigint
        constraint fkq83pan5xy2a6rn0qsl9bckqai
            references room
);

alter table booking owner to postgres;

