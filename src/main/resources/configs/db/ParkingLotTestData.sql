use train_db;
drop table if exists ParkingLot;
create table ParkingLot
(
	id int primary key auto_increment
	
	,totalPositionNum int not null
	,currentPrice decimal(10,4) not null 
	,state tinyint default 0
	
	,name varchar(32) not null unique
	,location varchar(128)
	,cost decimal(20,4)
)engine=innodb auto_increment=1 charset='utf8';

insert into ParkingLot(totalPositionNum,currentPrice,cost,name,location)
values
 (4,1.33,100,'A','CC')
,(3,233,100,'B','CC')
,(3,3.33,100,'C','CC')
;


drop table if exists ParkingPosition;
create table ParkingPosition
(
	id bigint primary key auto_increment
	,parkingLotId int not null references ParkingLot(id)
	,accountId bigint
	,state tinyint default 0 
)engine=innodb auto_increment=1 charset='utf8';
insert into ParkingPosition(parkingLotId,accountId)
values
(1,1)
,(1,2),(2,5),(3,8 )
,(1,3),(2,6),(3,9 )
,(1,4),(2,7),(3,10)
;



drop table if exists Account;
create table Account
(
	id bigint primary key auto_increment
	,userId bigint references User(id)
	,parkingLotId int references ParkingLot(id)
	,parkingPositionId bigint references ParkingPosition(id)
	,state tinyint default 0
	,billStateDate datetime
	,billEndDate datetime
)engine=innodb auto_increment=1 charset='utf8';
insert into Account(userId,parkingLotId,parkingPositionId)
values
(1,1,1)
,(2 ,1,2 )
,(3 ,1,3 )
,(4 ,1,4 )
,(5 ,2,5 )
,(6 ,2,6 )
,(7 ,2,7 )
,(8 ,3,8 )
,(9 ,3,9 )
,(10,3,10)
;



drop table if exists User;
create table User
(
	id bigint primary key auto_increment
)engine=innodb auto_increment=1 charset='utf8';
insert into User values
(1)
,(2 )
,(3 )
,(4 )
,(5 )
,(6 )
,(7 )
,(8 )
,(9 )
,(10)
;


alter table ParkingPosition add foreign key(accountId) references Account(id);












