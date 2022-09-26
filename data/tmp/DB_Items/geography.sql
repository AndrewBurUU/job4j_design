create table region(
	id serial primary key,
	name varchar(255)
);

create table city(
	id serial primary key,
	name varchar(255),
	country_id int references region(id)
);

insert into region(name) values('Республика Бурятия');
insert into region(name) values('Ленинградская область');
insert into region(name) values('Иркутская область');
insert into region(name) values('Забайкальский край');

insert into city(name, country_id) values('Улан-Удэ', 1);
insert into city(name, country_id) values('Гусиноозерск', 1);
insert into city(name, country_id) values('Санкт-Петербург', 2);
insert into city(name, country_id) values('Волхов', 2);
insert into city(name, country_id) values('Иркутск', 3);
insert into city(name, country_id) values('Ангарск', 3);
insert into city(name, country_id) values('Чита', 4);
insert into city(name, country_id) values('Петровск-Забайкальский', 4);

select r.name Регион, c.name Город
from region r join city c on r.id = c.country_id;

select r.name Регион, c.name Город
from region r join city c on r.id = c.country_id
where r.name like '%Ленин%';

select r.name Регион, c.name Город
from region r join city c on r.id = c.country_id
where c.name like '%ск'