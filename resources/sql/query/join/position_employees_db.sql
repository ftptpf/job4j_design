/* Создаем таблицу позиций */
create table position(
     id serial primary key,
     name varchar(255)
 );
 
 /* Создаем таблицу работников */
create table employees(
     id serial primary key,
     name varchar(255),
     position_id int references position(id)
 );