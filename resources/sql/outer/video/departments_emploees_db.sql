/*Создаем таблицу departments*/
CREATE TABLE departments(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128)
);

/*Создаем таблицу employees*/
CREATE TABLE employees(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128),
	departments_id INT REFERENCES departments(id)
);

/*Заполняем таблицу данными*/
INSERT INTO departments(name) VALUES ('Развития');
INSERT INTO departments(name) VALUES ('Эксплуатации');
INSERT INTO departments(name) VALUES ('Внедрения');
INSERT INTO departments(name) VALUES ('Финансовый');
INSERT INTO departments(name) VALUES ('Экономический');

INSERT INTO employees(name, departments_id) VALUES ('Сергей', 1);
INSERT INTO employees(name, departments_id) VALUES ('Иван', NULL);
INSERT INTO employees(name, departments_id) VALUES ('Александр', 2);
INSERT INTO employees(name, departments_id) VALUES ('Игорь', 3);
INSERT INTO employees(name, departments_id) VALUES ('Виктор', 1);
INSERT INTO employees(name, departments_id) VALUES ('Юрий', 1);
INSERT INTO employees(name, departments_id) VALUES ('Геннадий', 1);
INSERT INTO employees(name, departments_id) VALUES ('Семён', 2);
INSERT INTO employees(name, departments_id) VALUES ('Николай', NULL);
INSERT INTO employees(name, departments_id) VALUES ('Евгений', 3);
INSERT INTO employees(name, departments_id) VALUES ('Глеб', 5);
INSERT INTO employees(name, departments_id) VALUES ('Ирина', 3);
INSERT INTO employees(name, departments_id) VALUES ('Ольга', NULL);

/*Запрос LEFT JOIN*/
SELECT * 
FROM employees e 
LEFT JOIN departments d 
ON e.departments_id = d.id;
/*Запрос RIGHT JOIN*/
SELECT * 
FROM employees e 
RIGHT JOIN departments d 
ON e.departments_id = d.id;
/*Запрос FULL JOIN*/
SELECT * 
FROM employees e FULL 
JOIN departments d 
ON e.departments_id = d.id;
/*Запрос CROSS JOIN*/
SELECT * 
FROM employees e 
CROSS JOIN departments d;

/*Запросы LEFT JOIN и RIGHT JOIN которые дают одинаковый результат*/
SELECT * 
FROM employees e 
LEFT JOIN departments d 
ON e.departments_id = d.id;

SELECT * 
FROM departments d 
RIGHT JOIN employees e 
ON e.departments_id = d.id;


SELECT * 
FROM departments d 
LEFT JOIN employees e 
ON e.departments_id = d.id;

SELECT * 
FROM employees e 
RIGHT JOIN departments d 
ON e.departments_id = d.id;

/*Создаем таблицу teens*/
CREATE TABLE teens(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128) NOT NULL,
	gender VARCHAR(128) NOT NULL
);

/*Заполняем таблицу teens данными*/
INSERT INTO teens(name, gender) VALUES ('Сергей', 'м');
INSERT INTO teens(name, gender) VALUES ('Виктория', 'ж');s
INSERT INTO teens(name, gender) VALUES ('Алла', 'ж');
INSERT INTO teens(name, gender) VALUES ('Света', 'ж');
INSERT INTO teens(name, gender) VALUES ('Ольга', 'ж');
INSERT INTO teens(name, gender) VALUES ('Виктор', 'м');
INSERT INTO teens(name, gender) VALUES ('Николай', 'м');
INSERT INTO teens(name, gender) VALUES ('Олег', 'м');
INSERT INTO teens(name, gender) VALUES ('Константин', 'м');

/*Запрос CROSS JOIN создание разнополых пар*/
SELECT t1.name AS Man, t2.name AS Woman 
FROM 
	(SELECT name FROM teens WHERE gender = 'м') t1 
CROSS JOIN 
	(SELECT name FROM teens WHERE gender = 'ж') t2; 
