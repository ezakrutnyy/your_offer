create user zak with encrypted password 'loko88';
create database your_offer;
grant all privileges on database your_offer to zak;


create table offer
(
    id              int8            not null,
    title           varchar(256)    not null,
    category        varchar(256)    not null,
    createDate      timestamp(6)    not null,
    changeDate      timestamp(6)    not null,
    startprice      numeric         not null,
    totalprice        numeric         not null,
    totaldays       int             not null,
    description     varchar(256)
);

create unique index offer_id_uindex
    on offer (id);

create unique index offer_pk
    on offer (id);

-- alter table offer
--     add constraint offer_pk
--         primary key (id);

-- postgres/admin