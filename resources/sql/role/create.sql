/* SQL скрипт создающий таблицы для хранения структуры системы заявок. */

/* Создаем таблицу ролей */
CREATE TABLE role (
	id serial primary key,
	role varchar(255)	-- поле роли (админ, модератор, пользователь)
);

/* Создаем таблицу прав ролей */
CREATE TABLE rules (
	id serial primary key,
	read boolean, -- права чтения
	write boolean, -- права записи
	execute boolean  -- права исполнения
);

/* Создаем промежуточную таблицу чтобы выполнить role - rules = many-to-many*/
CREATE TABLE role_rules (
	id serial primary key,
	role_id int references role(id), -- связываем с role(id)
	rules_id int references rules(id) -- связываем с rules(id)
);

/* Создаем таблицу пользователей, users - role = many-to-one */ 
CREATE TABLE users (
	id serial primary key,
	surname varchar(255), -- фамилия
	name varchar(255), -- имя
	patronymic varchar(255), -- отчество
	role_id int references role(id) -- связываем many-to-one таблицу users с таблицей role. У одной роли может быть много пользователей.
);

/* Создаем таблицу категорий заявок */
CREATE TABLE category (
	id serial primary key,
	importance varchar(255) -- важность заявки
);

/* Создаем таблицу состояний заявок */
CREATE TABLE state (
	id serial primary key,
	completed boolean -- выполнена заявка или нет
);


/* Создаем таблицу заявок */
CREATE TABLE item (
	id serial primary key,
	users_id int references users(id), -- связываем many-to-one таблицу item с таблицей users. У одного пользователя может быть много заявок.
	category_id int references category(id), -- связываем many-to-one таблицу item с таблицей категорий заявок. У одной категории может быть много заявок.
	state_id int references state(id) -- связываем many-to-one таблицу item с таблицей состояния заявок. У одного состояния может быть много заявок.
);

/* Создаем таблицу комментарии заявок */
CREATE TABLE comments (
	id serial primary key,
	txt varchar(255), -- текст комментария
	item_id int references item(id) -- связываем many-to-one таблицу comments с таблицей заявок. У одной заявки может быть много комментариев.

);

/* Создаем таблицу приложенных Файлов */
CREATE TABLE attachs (
	id serial primary key,
	file_name varchar(255), -- имя файла
	item_id int references item(id) -- связываем many-to-one таблицу attachs с таблицей заявок. У одной заявки может быть много приложенных файлов.

);

