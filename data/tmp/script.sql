create table main(
	id serial primary key,
	name text,
	age int,
	sex boolean
);
insert into main(name, age, sex) values('Andrew', 36, true);
update main set age = 37;
delete from main;
