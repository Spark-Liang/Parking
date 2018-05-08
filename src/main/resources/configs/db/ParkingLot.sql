create table ParkingLot
(
	id int primary key auto_increment
	,name varchar(32) not null unique key
	,position varchar(128)
	,cost double
)engine=innodb auto_increment=1 charset='ut8';


create table ParkingPosition
(
	id bigint primary key auto_increment
	,parkingLotId int not null references ParkingLot(id)
	,accountId bigint
	,usedFlag bit 
)engine=innodb auto_increment=1 charset='ut8';

--关联账户表
alter table ParkingPosition add foreign key(accountId) references 账户表(id) 