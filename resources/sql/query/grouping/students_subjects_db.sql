create table students(
    id serial primary key,
    name varchar(255)
);

create table subjects(
    id serial primary key,
    name varchar(255)
);

create table students_subjects(
    id serial primary key,
    student_id int references students(id),
    subject_id int references subjects(id),
    mark int -- оценка ученика
);