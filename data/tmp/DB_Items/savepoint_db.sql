-- create table products (
--     id serial primary key,
--     name varchar(50),
--     producer varchar(50),
--     count integer default 0,
--     price integer
-- );

-- insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
-- insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
-- insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

--select * from products;

-- begin transaction;
-- insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);
-- savepoint product5_savepoint;
-- select * from products;

-- delete from products where price = 115;
-- savepoint delete115_savepoint;
-- select * from products;

-- insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
-- select * from products;

-- rollback to delete115_savepoint;
-- select * from products;

-- rollback to product5_savepoint;
-- select * from products;

-- commit transaction;
-- select * from products;


