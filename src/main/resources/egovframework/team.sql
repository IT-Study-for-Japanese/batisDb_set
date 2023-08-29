select * from bike_reserve_place;

INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('두류역','대구광역시 서구 달구벌대로 1760');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('화원역','대구광역시 달성군 화원읍 비슬로 2600');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('동대구역','대구광역시 동구 동대구로 550');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('반월당역','대구광역시 중구 달구벌대로 2100');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('어린이회관역','대구광역시 수성구 동대구로 166 어린이회관역');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('영남대역','경상북도 경산시 대학로 270');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('대공원역','대구광역시 수성구 달구벌대로 2950');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('서부정류장역','대구광역시 남구 월배로 501');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('팔달시장역','대구광역시 북구 팔달로 163-1');
INSERT INTO bike_reserve_place(bike_reserve_place_name,bike_reserve_place_addr) VALUES('서문시장역','대구광역시 중구 달성로 50');

update bike_reserve_place set bike_reserve_place_name = '대공원역', bike_reserve_place_addr = '대구광역시 수성구 달구벌대로 2950' where bike_reserve_place_name = '영대병원역';

INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(1,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(2,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(3,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(4,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(5,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(6,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(7,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(8,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(9,11,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(10,11,true);

INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(11,12,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(12,12,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(13,12,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(14,12,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(15,12,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(16,13,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(17,13,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(18,13,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(19,14,true);
INSERT INTO bike(bike_id,bike_reserve_place_id,bike_status) values(20,14,true);




select MIN(bike_id) from bike where bike_reserve_place_id = 11 AND bike_status = TRUE;
desc reservation;
select * from bike_reserve_place;
select * from reservation;
select * from bike;

SELECT COUNT(*) AS count_available_reserve FROM bike WHERE bike_reserve_place_id = 11 AND bike_status = TRUE;

insert into user values('tkj','KIM','123',01088711061); 

alter table reservation modify column period time;
delete from reservation;
delete from bike;

insert into reservation(user_id, bike_id, period) values('tkj',7,'01:00:00');
