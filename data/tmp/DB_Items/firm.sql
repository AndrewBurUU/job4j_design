create table departments(
	id serial primary key,

);

create table employees(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

insert into departments(name) values('Math'), ('Physics'), ('CS');
insert into employees(name, departments_id) values('student1', 1), ('student2', 2), ('student3', 3), ('student4', 3);

select * from departments d 
left join employees e 
on d.id = e.departments_id;

select * from departments d 
right join employees e 
on d.id = e.departments_id;

select * from departments d 
full join employees e 
on d.id = e.departments_id;

select * from departments d 
cross join employees e;

select * from departments d 
left join employees e 
on d.id = e.departments_id 
where departments_id is null

select e.name, d.name 
from departments d 
left join employees e 
on d.id = e.departments_id;

select e.name, d.name 
from employees e 
right join departments d 
on d.id = e.departments_id;

create table teens(
	id serial primary key,
	name varchar(255),
	gender char(1)
);

insert into teens(name, gender) values('w1','w'), ('w2', 'w'), ('m1', 'm'), ('m2', 'm'), ('w3', 'w');
select t1.name name1, t2.name name2 
from teens t1 
cross join teens t2 
where t1.gender != t2.gender;

