/* SQL Select by company */

/*Создаем таблицу company*/
CREATE TABLE company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

/*Создаем таблицу person*/
CREATE TABLE person (
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

/*Значения таблицы company*/
SELECT * FROM company;

/*Заполняем данными таблицу company*/
INSERT INTO company (id, name) 
VALUES (1, 'A'),
	   (2, 'B'),
	   (3, 'C'),
	   (4, 'D'),
	   (5, 'E');

/*Заполняем данными таблицу person*/
INSERT INTO person (id, name, company_id)
VALUES (1, 'Ivan', 1),
	   (2, 'Oleg', 2),
	   (3, 'Semen', 3),
	   (4, 'Gorge', 4),
	   (5, 'Bill', 5),
	   (6, 'Alex', 1),
	   (7, 'Viktor', 2),
	   (8, 'Vladislav', 3),
	   (9, 'NIk', 4),
	   (10, 'Tom', 5),
	   (11, 'Timur', 1),
	   (12, 'Andrey', 2),
	   (13, 'Anatoliy', 3),
	   (14, 'Konstantin', 4),
	   (15, 'Yakov', 5),
	   (16, 'Kiril', 5);

/*Значения таблицы person*/
SELECT * FROM person;

/*В одном запросе получаем
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.*/
SELECT p.name AS name, c.name AS company
FROM person AS p
JOIN company AS c 
ON c.id = p.company_id
WHERE c.id != 5;


/*Необходимо выбрать название компании с максимальным количеством человек 
+ количество человек в этой компании.*/
SELECT c.name AS company, COUNT(p.id) AS "person quantity"
FROM person AS p
JOIN company AS c
ON c.id = p.company_id
GROUP BY c.name
ORDER BY "person quantity" DESC
LIMIT 1;
