create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.13
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax();

insert into products (name, producer, count, price) 
VALUES ('product1', 'producer1', 8, 100);

insert into products (name, producer, count, price) 
VALUES ('product2', 'producer2', 3, 10);

select * from products;

alter table products disable trigger tax_trigger;

create or replace function tax_row()
	returns trigger as
$$
	BEGIN
		NEW.price = NEW.price + NEW.price * 0.13;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
	before insert
	on products
	for each row
	execute procedure tax_row();

insert into products (name, producer, count, price) 
VALUES ('product3', 'producer3', 30, 1000);

select * from products;

alter table products disable trigger tax_row_trigger;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function copy_row_to_history()
	returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date)
		values(NEW.name, NEW.price, current_date);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger copy_to_history
	after insert
	on products
	for each row
	execute procedure copy_row_to_history();

insert into products (name, producer, count, price) 
VALUES ('product4', 'producer1', 1, 100);

select * from products;
select * from history_of_price;