create table cars(
    id serial primary key,
    brand text,
    year serial,
	owner varchar(255)
);
insert into cars (brand, year, owner) values('BWM', 1998, 'Иван');
update cars set owner = 'Igor';
delete from cars;
select * from cars;