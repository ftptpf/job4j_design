/*Создаем таблицу кузов body*/
CREATE TABLE body(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128)
);

/*Создаем таблицу двигатель engine*/
CREATE TABLE engine(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128)
);

/*Создаем таблицу коробка передач transmission*/
CREATE TABLE transmission(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128)
);

/*Создаем таблицу автомобилей car*/
CREATE TABLE car(
	id SERIAL PRIMARY KEY,
	name VARCHAR(128),
	body_id INT REFERENCES body(id),
	engine_id INT REFERENCES engine(id),
	transmission_id INT REFERENCES transmission(id)
);

/*Заполняем таблицу данными*/
INSERT INTO body(name) VALUES ('Седан');
INSERT INTO body(name) VALUES ('Универсал');
INSERT INTO body(name) VALUES ('Хетчбек');
INSERT INTO body(name) VALUES ('Минивен');
INSERT INTO body(name) VALUES ('Внедорожник');
INSERT INTO body(name) VALUES ('Пикап');

INSERT INTO engine(name) VALUES ('дизель');
INSERT INTO engine(name) VALUES ('бензин');
INSERT INTO engine(name) VALUES ('гибрид');
INSERT INTO engine(name) VALUES ('электро');

INSERT INTO transmission(name) VALUES ('механика');
INSERT INTO transmission(name) VALUES ('автомат');
INSERT INTO transmission(name) VALUES ('робот');

INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('BMW', 1, 1, 2);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('BMW', 2, 2, 1);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('LADA', 3, 1, 1);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('LADA', 2, 4, 2);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('Opel', 4, 1, 1);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('Opel', 2, 2, 2);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('Opel', 2, 2, 1);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('Chevrolet', 5, 4, 1);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('Chevrolet', 1, 1, 2);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('Chevrolet', 3, 2, 1);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES ('AUDI', NULL, NULL, NULL);

/*Запрос на вывод списка всех машин и всех привязанных к ним деталей*/
SELECT c.name AS Авто, b.name AS Кузов, e.name AS Двигатель, t.name AS "Коробка передач"
FROM car c
LEFT JOIN body b
ON c.body_id = b.id
LEFT JOIN engine e
ON c.engine_id = e.id
LEFT JOIN transmission t
ON c.transmission_id = t.id
ORDER BY c.name;

/*Запросы на вывод отдельно деталей (1 деталь - 1 запрос), которые не используются в машине, кузова, двигатели, коробки передач*/

/*Кузов*/
SELECT b.name AS Кузов
FROM car c
RIGHT JOIN body b
ON c.body_id = b.id
WHERE c.name IS NULL;

/*Двигатель*/
SELECT e.name AS Двигатель
FROM car c
RIGHT JOIN engine e
ON c.engine_id = e.id
WHERE c.name IS NULL;

/*Коробка передач*/
SELECT t.name AS "Коробка передач"
FROM car c
RIGHT JOIN transmission t
ON c.transmission_id = t.id
WHERE c.name IS NULL;