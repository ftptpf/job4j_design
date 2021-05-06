/*Запросы к базе данных devices_people_db*/

/*Запрос на вывод средней цены устройств*/
select avg(d.price) from devices_people as dp 
join people as p on dp.people_id = p.id 
join devices as d on dp.device_id = d.id;

/*Запрос на вывод средней цены устройств каждого пользователя*/
select p.name, avg(d.price) from devices_people as dp 
join people as p on dp.people_id = p.id 
join devices as d on dp.device_id = d.id
group by p.name;

/*Запрос на вывод средней цены устройств каждого пользователя, при условии что средняя стоимость устройств должна быть больше 20000*/
select p.name, avg(d.price) from devices_people as dp 
join people as p on dp.people_id = p.id 
join devices as d on dp.device_id = d.id
group by p.name having avg(d.price) > 20000;
