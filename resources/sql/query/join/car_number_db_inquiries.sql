/*Запросы к таблицам базы car_number_db*/

/*Для каждого автомобиля извлекаем госномер*/
select * from car join number n on car.number_id = n.id;

/*Альясы*/

/*Для каждого автомобиля извлекаем регион*/
select c.name, n.code from car as c join number as n on c.number_id = n.id;

/*Для каждого автомобиля извлекаем регион, меняем поля */
select c.name as "Марка авто", n.code as "Регион регистрации" from car as c join number as n on c.number_id = n.id;

/*Выводим данные автомобиля зарегистрированного в регионе 177*/
select c.name, n.seria, n.number, n.add_serria, n.code from car as c join number as n on c.number_id = n.id where n.code = 177;
