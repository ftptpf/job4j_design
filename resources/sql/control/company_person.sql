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

/*В одном запросе получаем
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.*/




/*Необходимо выбрать название компании с максимальным количеством человек 
+ количество человек в этой компании.*/