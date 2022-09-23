create table product(
	id serial primary key,
	name varchar(255)
);

create table shop(
	id serial primary key,
	product_id int references product(id),
	count int
);