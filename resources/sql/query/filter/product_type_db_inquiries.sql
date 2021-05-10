/*Запросы к базе данных product_type_db*/

/*Запрос получение всех продуктов с типом "СЫР"*/
SELECT p.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	WHERE t.name = 'СЫР';

/*Запрос получения всех продуктов, у кого в имени есть слово "мороженное"*/
SELECT name 
	FROM product 
	WHERE name LIKE '%мороженно%';	

/*Запрос выводит все продукты, срок годности которых заканчивается в следующем месяце.
Запрос универсальным, т.е. не зависит от конкретного временного промежутка*/
SELECT name 
	FROM product
	WHERE (EXTRACT(MONTH FROM expired_date) =  EXTRACT(MONTH FROM (CURRENT_DATE + INTERVAL'1 MONTH')));	
	

/*Запрос выводит самый дорогой продукт.*/
SELECT name
	FROM product
	WHERE price = (SELECT MAX(price) FROM product);

/*Запрос который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество.*/
SELECT t.name, COUNT(p.name) 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	GROUP BY t.name;

/*Запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
SELECT p.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	WHERE t.name = 'СЫР' or t.name ='МОЛОКО';

/*Запрос который выводит тип продуктов, которых осталось меньше 10 штук*/
SELECT t.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	GROUP BY t.name
	HAVING COUNT(p.name) < 10;

/*Запрос вывести все продукты и их тип*/
SELECT p.name, t.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id ;
