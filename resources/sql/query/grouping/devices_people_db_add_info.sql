/*Заполняем базу данных devices_people_db информацией*/

/*Заполняем информацию о пользователях*/
insert into people(name) values ('Игорь');
insert into people(name) values ('Света');
insert into people(name) values ('Николай');

/*Заполняем информацию о устройствах*/
insert into devices(name, price) values ('Планшет', 12200.12);
insert into devices(name, price) values ('ПК', 100000.01);
insert into devices(name, price) values ('Проектор', 33000.50);
insert into devices(name, price) values ('Навигатор', 14000.20);

/*Заполняем many-to-many информацию о пользователях и устройствах*/
insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);

insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (2, 3);

insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (3, 3);

insert into devices_people(device_id, people_id) values (4, 1);
insert into devices_people(device_id, people_id) values (4, 3);