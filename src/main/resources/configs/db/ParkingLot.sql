create table ParkingLot
(
	id int primary key auto_increment
	
	,totalPositionNum int not null
	,currentPrice decimal(10,4) not null 
	,state tinyint default 0
	
	,name varchar(32) not null unique key
	,location varchar(128)
	,cost decimal(20,4)
)engine=innodb auto_increment=1 charset='utf8';



create table ParkingPosition
(
	id bigint primary key auto_increment
	,parkingLotId int not null references ParkingLot(id)
	,accountId bigint
	,state tinyint default 0 
)engine=innodb auto_increment=1 charset='utf8';

--关联账户表
alter table ParkingPosition add foreign key(accountId) references 账户表(id) 

create table Account
(
	id bigint primary key auto_increment
	,userId bigint references User(id)
	,parkingLotId int references ParkingLot(id)
	,parkingPositionId bigint references ParkingPosition(id)
	,cardId bigint
	
	,state tinyint default 0
	,currentBill bigint
	
)engine=innodb auto_increment=1 charset='utf8';

create table Bill
(
	id bigint primary key auto_increment
	,userId int key
	,parkingLotId int 
	,accountId bigint key
	
	,price decimal(10,4)
	,billStartDate datetime
	,billEndDate datetime
	,isPaid bit not null 
)engine=innodb auto_increment=1 charset='utf8';