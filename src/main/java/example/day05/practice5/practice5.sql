drop database if exists mydb0304;
create database mydb0304;
use mydb0304;
create table exam(
eno int primary key auto_increment,
ename varchar(255)
);
insert into exam(ename) values('유재석');
insert into exam(ename) values('강호동');
insert into exam(ename) values('신동엽');
select*from exam;

create table book( 
# sql에서는 영문 대소문자 구분 안 해서 카멜표기법이 없음 ex) myCar > my_car
bno int primary key auto_increment,
bid varchar(255),
bname varchar(100) not null,
bwriter varchar(50) not null,
bpublish varchar(50) not null
);

insert into book (bid, bname, bwriter, bpublish) values
('B001', '파이썬 데이터 분석 실무', '김철수', 'IT메카'),
('B002', '오늘의 미니멀리즘', '이영희', '공감출판'),
('B003', '자바스크립트의 정석', '박지성', '코딩월드'),
('B004', '마케팅의 심리학', '최유리', '비즈니스북스'),
('B005', '기초 SQL 가이드', '정민호', '데이터하우스');
select*from book;