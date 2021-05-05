/* Создаем таблицу паспортов */
create table passport(
	id serial primary key,
	seria int,
	number int
);

/* Создаем таблицу людей */
create table people(
	id serial primary key,
	name varchar(255),
	passport_id int references passport(id) unique
);