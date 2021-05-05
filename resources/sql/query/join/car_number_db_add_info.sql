/* Добавляем информацию в таблицы базы car_number_db */

insert into number(seria, number, add_serria, code) values ('А', 980, 'ЕМ', 177);
insert into number(seria, number, add_serria, code) values ('М', 190, 'ЗТ', 170);
insert into number(seria, number, add_serria, code) values ('У', 001, 'КЕ', 199);

insert into car(name, number_id) values ('Chevrolet', 1);
insert into car(name, number_id) values ('LADA', 2);
insert into car(name, number_id) values ('BMW', 3);
insert into car(name) values ('Opel');
insert into car(name) values ('Toyota');