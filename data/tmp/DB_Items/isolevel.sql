-- Database: isolevel

-- DROP DATABASE IF EXISTS isolevel;

CREATE DATABASE isolevel
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

create table car (
    id serial primary key,
    name varchar(50),
    price integer
);

insert into car (name, price) VALUES ('car1', 10000);
insert into car (name, price) VALUES ('car2', 15000);
insert into car (name, price) VALUES ('car3', 20000);

select * from car;
select sum(price) from car;

update car 
set price = 20000 
where name = 'car3';

update car 
set price = 12000 
where name = 'car1';
