create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
); 

insert into products (name, producer, count, price) 
VALUES ('product1', 'producer1', 8, 100);

insert into products (name, producer, count, price) 
VALUES ('product2', 'producer2', 3, 10);

insert into products (name, producer, count, price) 
VALUES ('product3', 'producer3', 30, 1000);

insert into products (name, producer, count, price) 
VALUES ('product4', 'producer1', 1, 100);
 
create or replace procedure delete_data(i_id integer)
language 'plpgsql'
as $$
	BEGIN
		delete from products where id = i_id;
	END
$$;

call delete_data(2);

create or replace function f_del_data(i_id integer)
returns void
language 'plpgsql'
as
$$
	begin
		delete from products where id = i_id;
	end;
$$;

select f_del_data(3);

select * from products;