create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type(name) values('СЫР'),('МАСЛО'),('МОЛОКО');

insert into product(name, type_id, expired_date, price) 
values('сыр1', 1, '01.10.2022', 500), ('молоко замороженое', 3, '01.12.2022', 120);

insert into product(name, type_id, expired_date, price) 
values('сыр2', 1, '01.09.2022', 1500), ('масло1', 2, '05.10.2022', 150);

insert into product(name, type_id, expired_date, price) 
values('молоко1', 3, '01.09.2022', 90), ('масло2', 2, '05.10.2022', 200);

select p.name, t.name, p.price, p.expired_date
from type t join product p on t.id = p.type_id
where t.name = 'СЫР';

select * from product where name like '%мороженое%';
select * from product where expired_date < current_date;

select p1.name, p1.price
from product p1 
join 
(select max(price) as pprice
 from product p2) p3
on p1.price = p3.pprice;

select t.name, count(p.id)
from type t join product p on t.id=p.type_id
group by t.name;

select p.name
from type t join product p on t.id=p.type_id
where t.name = 'СЫР' OR t.name = 'МОЛОКО';

select t.name, count(p.id)
from type t join product p on t.id=p.type_id
group by t.name
having count(p.id) < 10;

select p.name, t.name 
from product p 
join type t on p.type_id=t.id;
