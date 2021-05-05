/* Создаем таблицу государственных номеров автомобиля */
create table number(
	id serial primary key,
	seria varchar(255),
	number int,
	add_serria varchar(255),
	code int
);

/* Создаем таблицу автомобилей */
create table car(
	id serial primary key,
	name varchar(255),
	number_id int references number(id) unique
);