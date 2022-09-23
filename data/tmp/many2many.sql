create table product(
	id serial primary key,
	name varchar(255)
);

create table branchshop(
	id serial primary key,
	name varchar(255),
	address varchar(255)
);

create table shops(
	id serial primary key,
	shop_id int references branchshop(id),
	product_id int references product(id)
)