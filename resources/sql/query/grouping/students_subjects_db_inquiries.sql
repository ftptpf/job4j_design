/*Запросы к базе данных students_subjects_db*/

select avg(mark) from students_subjects; -- средний бал
select min(mark) from students_subjects; -- минимальная оценка
select max(mark) from students_subjects; -- максимальная оценка

/*Запрос выводит средний бал по предмету.
В данном случае признаком выступает имя предмета, по нему производиться группировка, 
группы же будут состоять из других столбцов. В частности, среди этих групп окажется оценка по предмету, 
к которой мы и применяем агрегатную функцию.*/
select s.name, avg(ss.mark) from students_subjects as ss join subjects as s on ss.subject_id = s.id group by s.name;

/*Запрос по среднему баллу ученика будет аналогичным.*/
select s.name, avg(ss.mark) from students_subjects as ss join students as s on ss.subject_id = s.id group by s.name;

/*Запрос, находим предмет, по которому ученики набрали более 4.5 баллов*/
select s.name, avg(ss.mark) from students_subjects as ss join subjects s on ss.subject_id = s.id group by s.name having avg(ss.mark) > 4.5;