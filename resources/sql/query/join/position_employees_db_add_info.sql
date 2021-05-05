/* Добавляем информацию в таблицы базы position_employees_db */

insert into position(name) values ('programmer');
insert into position(name) values ('project manager');
insert into position(name) values ('director');

insert into employees(name, position_id) values('Boris', 1);
insert into employees(name, position_id) values('Ivan', 1);
insert into employees(name, position_id) values('Kiril', 1);
insert into employees(name, position_id) values ('Marina', 2);
insert into employees(name, position_id) values ('Pers', 3);

insert into employees(name) values ('Alexander');