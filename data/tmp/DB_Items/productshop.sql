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

insert into product(name, type_id, expired_date, price) values('сыр1', 1, '01.10.2022', 500), ('молоко замороженое', 3, '01.12.2022', 120);
insert into product(name, type_id, expired_date, price) values('сыр2', 1, '01.09.2022', 1500), ('масло1', 2, '05.10.2022', 150);
insert into product(name, type_id, expired_date, price) values('молоко1', 3, '01.09.2022', 90), ('масло2', 2, '05.10.2022', 200);

select * from product where type_id = 1;
select * from product where name like '%мороженое%'
select * from product where expired_date < '26.09.2022'
select max(price) from product
select type_id, count(id) from product group by type_id
select name from product where type_id = 1 OR type_id = 3
select type_id, count(id) from product group by type_id having count(id) < 10
select p.name, t.name from product p join type t on p.type_id=t.id

