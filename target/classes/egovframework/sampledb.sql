select * from tab;

select * from test

drop table test;

create table test(
id varchar2(12) primary key,
name varchar2(20));

delete sample;
select * from sample;

insert into sample values('SAMPLE-00001', 'JAVA Programmin','관리자','JAVA 관련 글만 등록하세요.',sysdate);

create table ids(
table_name varchar2(16) primary key,
next_id number(30) not null
);
drop table ids;
insert into ids values('sample',2);
select * from ids;
update ids set next_id=2 where table_name='sample'
insert into sample(id,title,reg_user,content,reg_date) values 
((select nvl(max(id),0)+1 from sample),?,?,?,sysdate);

insert into sample(id,title,reg_user,content,reg_date) values 
((select nvl(max(id),0)+1 from sample),'제목4','작성자4','본문내용4',sysdate);


select nvl(max(id),0)+1 from sample
select max(id) from sample

update sample set title=?, reg_user=?, content=? where id=?

update sample set title='제목2', reg_user='작성자2', 
content='본문내용2' where id=1;

delete from sample where id=?
delete from sample where id=2

select id,title,reg_user,content,reg_date from sample where id=?

select id,title,reg_user,content,reg_date from sample order by reg_date desc;

CREATE TABLE bike_reserve_place ( 
	bike_reserve_place_id INTEGER AUTO_INCREMENT primary KEY, 
	bike_reserve_place_name VARCHAR(30),
	bike_reserve_place_addr VARCHAR(60));

select * from bike_reserve_place;
SELECT * FROM bike_reserve_place WHERE bike_reserve_place_addr LIKE '%'#reservePlaceName#'%' OR bike_reserve_place_name LIKE '%'#reservePlaceName#'%';

select * from bike_reserve_place;
