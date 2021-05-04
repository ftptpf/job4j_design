/* SQL скрипт заполняющий начальные данные для системы заявок. */

/* Вставляем данные в таблицу role*/
insert into role(role) values('админ');
insert into role(role) values('модератор');
insert into role(role) values('пользователь');

/* Вставляем данные в таблицу rules*/
insert into rules(read, write, execute) values(TRUE, FALSE, FALSE);
insert into rules(read, write, execute) values(TRUE, TRUE, FALSE);
insert into rules(read, write, execute) values(TRUE, TRUE, TRUE);

/* Вставляем данные в промежуточную таблицу role_rules*/
insert into role_rules(role_id, rules_id) values(1, 3); -- админ
insert into role_rules(role_id, rules_id) values(2, 2); -- модератор
insert into role_rules(role_id, rules_id) values(3, 1); -- пользователь

/* Вставляем данные в таблицу users*/
insert into users(surname, name, patronymic, role_id) values('Иванов', 'Иван', 'Иванович', 1);
insert into users(surname, name, patronymic, role_id) values('Петров', 'Петр', 'Петрович', 2);
insert into users(surname, name, patronymic, role_id) values('Сергев', 'Сергей', 'Сергеевич', 3);
insert into users(surname, name, patronymic, role_id) values('Игорев', 'Игорь', 'Игоревич', 3);

/* Вставляем данные в таблицу category*/
insert into category(importance) values('очень важно');
insert into category(importance) values('важно');
insert into category(importance) values('не важно');

/* Вставляем данные в таблицу state*/
insert into state(completed) values(TRUE); -- выполнена
insert into state(completed) values(FALSE); -- не выполнена

/* Вставляем данные в таблицу item*/
insert into item(users_id, category_id, state_id) values(3, 3, 2);
insert into item(users_id, category_id, state_id) values(4, 2, 1);
insert into item(users_id, category_id, state_id) values(1, 1, 1);
insert into item(users_id, category_id, state_id) values(2, 3, 1);

/* Вставляем данные в таблицу comments*/
insert into comments(txt, item_id) values('Поправьте еще 14 строку.', 1);
insert into comments(txt, item_id) values('Проверьте кодировку.', 1);
insert into comments(txt, item_id) values('Спасибо!', 2);
insert into comments(txt, item_id) values('Ок', 3);
insert into comments(txt, item_id) values('Закрывайте задачу.', 4);

/* Вставляем данные в таблицу attachs*/
insert into attachs(file_name, item_id) values('task1.java', 1);
insert into attachs(file_name, item_id) values('insert.sql', 1);
insert into attachs(file_name, item_id) values('target.sql', 2);
insert into attachs(file_name, item_id) values('some.jpg', 3);
insert into attachs(file_name, item_id) values('other.pdf', 4);
