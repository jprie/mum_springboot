create table if not exists Course
(
    id    bigint      not null auto_increment primary key,
    title varchar(40) not null
);

create table if not exists Teacher
(
    id bigint not null auto_increment primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    address_id bigint not null
);

create table if not exists Student
(
    id bigint not null auto_increment primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(30) not null,
    phone varchar(30) not null,
    address_id bigint not null
);

create table if not exists Course_Location
(
    id bigint not null auto_increment primary key,
    room_nr varchar(9) not null

);

create table if not exists Address
(
    id bigint not null auto_increment primary key,
    street varchar(20),
    street_nr varchar(5),
    door_nr varchar(3)
);

create table if not exists Location
(
    plz varchar(5) not null,
    city varchar(20) not null,
    country varchar(30) not null,
    primary key (plz, country)
);

create table if not exists Teacher_Course (
    teacher_id bigint not null,
    course_id bigint not null,
    primary key (teacher_id, course_id)
);

create table if not exists Student_Course
(
    student_id bigint not null,
    course_id  bigint not null,
    primary key (student_id, course_id)
);

alter table Teacher_Course add foreign key (teacher_id) references Teacher(id);
alter table Teacher_Course add foreign key (course_id) references Course(id);

alter table Student_Course add foreign key (student_id) references Student(id);
alter table Student_Course add foreign key (course_id) references Course(id);

alter table Student add foreign key (address_id) references Address(id) on delete cascade;
alter table Teacher add foreign key (address_id) references Address(id);

insert into Address (street, street_nr, door_nr) VALUES ( 'Dorfstrasse', 3, 12 );
insert into Address (street, street_nr, door_nr) VALUES ( 'Hauptplatz', 14, 5 );
insert into Address (street, street_nr, door_nr) VALUES ( 'Baustrasse', 12, 1 );

insert into Student (first_name, last_name, email, phone, address_id) values ( 'Johannes', 'Priebsch', 'johannes@priebsch.at', '06505656767', 1);
insert into Student (first_name, last_name, email, phone, address_id) values ( 'Hans', 'Bauer', 'hans.bauer@gmail.com', '06601234567', 2);
insert into Student (first_name, last_name, email, phone, address_id) values ( 'Lisa', 'Maier', 'lisamaier@gmail.com', '06603333567', 3);

insert into Course (title)  values ( 'Java Anfänger' );
insert into Course (title)  values ( 'Kotlin Anfänger' );
insert into Course (title)  values ( 'Spring Boot' );

insert into Student_Course (student_id, course_id) VALUES ( 1, 2 ), (2, 2), (3, 1);

