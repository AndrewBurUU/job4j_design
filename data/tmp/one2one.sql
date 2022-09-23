create table director(
	id serial primary key,
	name varchar(255)
);

create table branchshop(
	id serial primary key,
	name varchar(255),
	address varchar(255),
	director_id int references director(id)
);