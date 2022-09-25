create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('fish', 11000, null);
insert into fauna(name, avg_age, discovery_date) values('monkey', 25000, '1949-01-01');
insert into fauna(name, avg_age, discovery_date) values('animal1', 9000, '2000-01-01');

select *
from fauna
where name like '%fish%';

select *
from fauna
where avg_age > 10000 and avg_age < 21000;

select *
from fauna
where discovery_date is null;

select *
from fauna
where discovery_date < '01.01.1950';
