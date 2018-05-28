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
	,userId bigint
	,parkingLotId int 
	,parkingPositionId bigint 
	,cardId bigint unique key 
	
	
	,state tinyint default 0
	,stateStartDate datetime not null
	,isParking bit not null default 0
	,currentBillId bigint
	
	,index userId_idx(userId)
	,index parkingLotId_idx(parkingLotId)
)engine=innodb auto_increment=1 charset='utf8';


create table Bill
(
	id bigint primary key auto_increment
	,userId int 
	,parkingLotId int 
	,accountId bigint 
	
	,price decimal(10,4)
	,billStartDate datetime
	,billEndDate datetime
	,isPaid bit not null 

	,index userId_idx(userId)
	,index accountId_idx(accountId)
)engine=innodb auto_increment=1 charset='utf8';
