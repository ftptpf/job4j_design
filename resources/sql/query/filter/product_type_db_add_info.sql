/*Заполняем базу данных product_type_db информацией*/

/*Заполняем информацией таблицу type*/
insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('МОРОЖЕННОЕ');

/*Заполняем информацией таблицу product*/
/*СЫР*/
insert into product(name, expired_date, price, type_id) values ('Российский сыр', '2021-05-20', 550.50, 1);
insert into product(name, expired_date, price, type_id) values ('Белорусский сыр', '2021-06-01', 660.50, 1);
insert into product(name, expired_date, price, type_id) values ('Украинский сыр', '2021-05-30', 750.50, 1);
insert into product(name, expired_date, price, type_id) values ('Адыгейский сыр', '2021-05-22', 350.50, 1);
insert into product(name, expired_date, price, type_id) values ('Грузинский сыр', '2021-06-10', 450.50, 1);
insert into product(name, expired_date, price, type_id) values ('Французкий сыр', '2021-05-21', 1550.50, 1);
insert into product(name, expired_date, price, type_id) values ('Немецкий сыр', '2021-05-22', 650.50, 1);
insert into product(name, expired_date, price, type_id) values ('Испанский сыр', '2021-06-21', 850.50, 1);
insert into product(name, expired_date, price, type_id) values ('Финский сыр', '2021-05-15', 950.50, 1);
insert into product(name, expired_date, price, type_id) values ('Итальянский сыр', '2021-05-31', 890.50, 1);
insert into product(name, expired_date, price, type_id) values ('Чешский сыр', '2021-05-30', 770.50, 1);

/*МОЛОКО*/
insert into product(name, expired_date, price, type_id) values ('Натуральное молоко', '2021-05-15', 100, 2);
insert into product(name, expired_date, price, type_id) values ('Домашнее молоко', '2021-05-25', 150, 2);
insert into product(name, expired_date, price, type_id) values ('Топленое молоко', '2021-05-20', 90, 2);
insert into product(name, expired_date, price, type_id) values ('Эконом молоко', '2021-06-10', 50, 2);
/*МОРОЖЕННОЕ*/
insert into product(name, expired_date, price, type_id) values ('Лакомка мороженное', '2021-06-05', 50, 3);
insert into product(name, expired_date, price, type_id) values ('Эскимо мороженное', '2021-06-01', 60, 3);
insert into product(name, expired_date, price, type_id) values ('Мороженное 36 коп', '2021-05-25', 70, 3);
