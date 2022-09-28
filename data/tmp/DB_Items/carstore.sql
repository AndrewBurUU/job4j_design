create table carbodies(
	id serial primary key,
	name varchar(255)
);

create table carengines(
	id serial primary key,
	name varchar(255)
);

create table cartransmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references carbodies(id),
	engine_id int references carengines(id),
	transmission_id int references cartransmissions(id)
);

insert into carbodies(name) values('седан'), ('хэтчбек'), ('пикап');
insert into carengines(name) values('бензин'), ('дизель');
insert into cartransmissions(name) values('механика'), ('автомат'), ('вариатор');

insert into cars(name) values('car0');
insert into cars(name, body_id, engine_id, transmission_id) values('car1', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('car2', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('car3', 2, 1, 1);

select c.id, c.name, cb.name, c.engine_id, c.transmission_id
from cars c join carbodies cb on c.body_id = cb.id

select cb.name
from carbodies cb left join cars c on cb.id = c.body_id 
where body_id is null

select ce.name
from carengines ce left join cars c on ce.id = c.engine_id 
where engine_id is null

select ct.name
from cartransmissions ct left join cars c on ct.id = c.transmission_id 
where body_id is null