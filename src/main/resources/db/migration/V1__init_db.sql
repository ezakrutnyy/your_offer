
alter table if exists offer
    drop constraint if exists offer_user_fk;

alter table if exists user_role
    drop constraint if exists role_user_fk;

drop table if exists offer cascade;

drop table if exists user_role cascade;

drop table if exists usr cascade;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1 increment 1;

create table offer
(
    id            int8 not null,
    category      varchar(255) not null,
    change_date   timestamp not null,
    create_date   timestamp not null,
    current_price int8,
    description   varchar(2048),
    filename      varchar(255),
    start_price   int8 not null,
    title         varchar(255) not null,
    total_days    int4 not null,
    user_id       int8 not null,
    primary key (id)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table usr
(
    id              int8  not null,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);

alter table if exists offer
    add constraint offer_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint role_user_fk
    foreign key (user_id) references usr;