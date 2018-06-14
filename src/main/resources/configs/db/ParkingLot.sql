create table ParkingLot
(
	id int primary key auto_increment
	
	,totalPositionNum int not null
	,currentPrice decimal(10,4) not null  default 10
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


create table Account
(
	id bigint primary key auto_increment
	,userId bigint
	,parkingLotId int 
	,parkingPositionId bigint 
	,cardId bigint unique key 
	
	,price decimal(10,4)
	,state tinyint default 0
	,isParking bit not null default 0
	
	,currentParkingRecId bigint  #此字段只用于实现统计停车次数功能
	,currentStateLogId bigint  #此字段只用于记录Account的State状态变化
	
	,index userId_idx(userId)
	,index parkingLotId_idx(parkingLotId)
)engine=innodb auto_increment=1 charset='utf8';

create table AccountStateLog
(
	id bigint primary key 
	,accountId bigint not null
	,state tinyint not null
	,startTime datetime not null
	,endTime datetime
	,billId bigint
	
	,index billId_idx(billId)
)engine=innodb auto_increment=1 charset='utf8';


create table Bill
(
	id bigint primary key auto_increment
	,userId bigint 
	,parkingLotId int 
	,accountId bigint 
	
	,price decimal(10,4)

	,lastPayDate date not null  #用户最后的支付的截止日期
	,isPaid bit not null default 0

	,index userId_idx(userId)
	,index accountId_idx(accountId)
)engine=innodb auto_increment=1 charset='utf8';

create table currentBillMap
(
	id bigint primary key auto_increment
	,accountId bigint not null
	,currentBillId bigint not null
	
	,index accountId_idx(accountId)
)engine=innodb auto_increment=1 charset='utf8'
;

create table ParkingRecord
(
	id bigint primary key auto_increment
	,lotId int not null
	,positionId bigint not null
	,accountId bigint not null
	,startTime datetime not null
	,endTime datetime
)engine=innodb auto_increment=1 charset='utf8';




