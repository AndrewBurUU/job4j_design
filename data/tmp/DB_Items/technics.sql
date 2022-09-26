create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Samsung1', 4000), ('Samsung2', 5500), ('Samsung3', 30000);
insert into devices(name, price) values('Realme1', 3000), ('Realme2', 3500), ('Realme3', 15000);
insert into devices(name, price) values('Apple1', 30000), ('Apple2', 90000), ('Apple3', 150000);

insert into people(name) values('Andrew'), ('Peter'), ('Alex');

insert into devices_people(people_id, device_id) values(1, 1), (1, 4), (1, 9);
insert into devices_people(people_id, device_id) values(2, 2), (2, 5), (2, 7);
insert into devices_people(people_id, device_id) values(3, 4);

select avg(price)
from devices

select dp.people_id, avg(d.price)
from devices d join devices_people dp on d.id = dp.device_id
group by dp.people_id

select dp.people_id, avg(d.price)
from devices d join devices_people dp on d.id = dp.device_id
group by dp.people_id
having avg(d.price) > 5000



