use train_db;


drop table if exists ParkingLot;
create table ParkingLot
(
	id int primary key auto_increment
	
	,totalPositionNum int not null
	,currentPrice decimal(10,4) not null  default 10
	,state tinyint default 0
	
	,name varchar(32) not null unique
	,location varchar(128)
	,cost decimal(20,4)
)engine=innodb auto_increment=1 charset='utf8';

insert into ParkingLot(totalPositionNum,currentPrice,cost,name,location)
values
 (4,10,100,'A','CC')
,(3,20,100,'B','CC')
,(6,30,100,'C','CC')
,(10,30,100,'TestAdd','CC')
;


drop table if exists ParkingPosition;
create table ParkingPosition
(
	id bigint primary key auto_increment
	,parkingLotId int not null
	,accountId bigint
	,state tinyint default 0 
)engine=innodb auto_increment=1 charset='utf8';
insert into ParkingPosition(parkingLotId,accountId,state)
values
 (1,1,1)
,(1,2,1),(2,5,1),(3,8 ,1)
,(1,3,1),(2,6,1),(3,9 ,1)
,(1,4,1),(2,7,1),(3,10,1),(3,11,1),(3,12,1),(3,13,1)
,(4,null,1),(4,15,1),(4,16,1),(4,null,0),(4,null,0),(4,null,0),(4,null,0),(4,null,0),(4,null,0),(4,null,0)
;



drop table if exists Account;

create table Account
(
	id bigint primary key auto_increment
	,userId bigint
	,parkingLotId int 
	,parkingPositionId bigint 
	,cardId bigint unique key 
	
	,price decimal(10,4)
	,state tinyint default 0
	#,stateStartDate datetime not null
	,isParking bit not null default 0
	
	,currentBillId bigint
	,currentParkingRecId bigint  #此字段只用于实现统计停车次数功能
	,currentStateLogId bigint #此字段只用于记录Account的State状态变化
	
	,index userId_idx(userId)
	,index parkingLotId_idx(parkingLotId)
)engine=innodb auto_increment=1 charset='utf8';

insert into Account(userId,parkingLotId,parkingPositionId,cardId
					,price,state
                    ,currentBillId,currentStateLogId)
values
 ('13745678910',1,1 ,'0000001',10,0,null,1 )
,('13745678911',1,2 ,'0000002',10,0,null,2 )
,('13745678912',1,3 ,'0000003',10,0,null,3 )
,('13745678913',1,4 ,'0000004',10,0,null,4 )
,('13745678914',2,5 ,'0000005',20,0,null,5 )
,('13745678915',2,6 ,'0000006',20,0,null,6 )
,('13745678916',2,7 ,'0000007',20,0,null,7 )
,('13745678917',3,8 ,'0000008',30,0,null,8 )
,('13745678918',3,9 ,'0000009',30,0,null,9 )
,('13745678919',3,10,'0000010',30,0,null,10)
,('13745678920',3,11,'0000011',30,0,null,11)
,('13719326102',3,12,'0000012',30,0,null,12)
,('13775119722',3,13,'0000013',30,0,null,13)
,('13745678920',4,14,'0000014',10,-1,1,16)#已欠费账号
,('13745678921',4,15,'0000015',10,0,2,18)#准备欠费的账户 ，当执行updateAllAccountState('2018-02-01 00:20:00',@tmp)变为欠费
,('13745678922',4,16,'0000016',10,0,3,22)#欠费再支付之后的账号
;

drop table if exists AccountStateLog;
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

insert into AccountStateLog (id,accountId,state,startTime,endTime,billId) 
values
 (1 ,1 ,0,'2018-02-01',null,null)
,(2 ,2 ,0,'2018-02-02',null,null)
,(3 ,3 ,0,'2018-02-03',null,null)
,(4 ,4 ,0,'2018-02-04',null,null)
,(5 ,5 ,0,'2018-02-05',null,null)
,(6 ,6 ,0,'2018-02-06',null,null)
,(7 ,7 ,0,'2018-02-07',null,null)
,(8 ,8 ,0,'2018-02-08',null,null)
,(9 ,9 ,0,'2018-02-09',null,null)
,(10,10,0,'2018-02-10',null,null)
,(11,11,0,'2018-02-11',null,null)
,(12,12,0,'2018-02-12',null,null)
,(13,13,0,'2018-02-13',null,null)
#已欠费账号
,(14,14,0,'2017-07-10 8:10:00','2018-09-01 0:20:00',1)
,(15,14,0,'2018-09-01 0:20:00','2018-10-01 0:20:00',null)
,(16,14,-1,'2018-10-01 0:20:00',null,null)
#准备欠费的账户
,(17,15,0,'2017-10-10 0:20:00','2017-01-01 0:20:00',2)
,(18,15,0,'2017-01-01 0:20:00',null,null)
#欠费再支付的账号
,(19,16,0,'2017-07-10 8:10:00','2018-09-01 0:20:00',3)
,(20,16,0,'2017-09-01 0:20:00','2018-10-01 0:20:00',null)
,(21,16,-1,'2017-10-01 0:20:00','2018-10-15 8:10:00',null)
,(22,16,0,'2017-10-15 8:10:00',null,null)
;



drop table if exists User;
create table if not exists User(
   id bigint primary key auto_increment,
   userId bigint not null unique key,
   password varchar(512) not null,
   salt varchar(512) not null
)engine=innodb auto_increment=1 charset='utf8';
insert into User(userId,password,salt) values
 ('13745678910','8ba79b95b4a4b5060977e810746af570830658f1be916a506566e3c7b92e1b385d8ba6b3b5d4d99b4bbed8577e3db76a6f4e120328aff93fa94cfa052b1dc6c3','bpr7OKJWvvvwWiOHiL5vlYx3oSwFZzHj1f7YW7LtBLMOuzWx7ZCqxTvv9vfxooef26aS9oHda7W56AU1LmFV2b7hdDQ142Cix0ritqCZFTybrDJGhCA53khlJG8wzbqr')
,('13745678911','225be671ef113ad92b6bf740a56e2fba63a0bccbb553323a62a0ee74c03f928da9459e802fcf5a74aec50599988c0c3efa4c63cdcbe30a240696fa5c12ade62e','xxpFzJfpjNYJvwOfDFyf64i7cQom6XRJdH6L1wo9BN7jhE2KfA3NqEo8jLWPzYZZwOyMEigH23MrPl5qqaNIMZ4RLH3Z99HJZdtw3lSEYmJzUY1xS0SiZXnZCPDOMNqK')
,('13745678912','22f2048889a0bb4e5e4756d9359738a72502214b26227990292c289702b5c59f5e857c354b311c49901655f9335af9bed5b225246bfb1faeef13a42774c1dc42','vp7htu3TzmAcSwhFAZFioyVA1AbyHq2PXRp1I2K15Rqe6RmpnIHEZnc6ArOw9hGZI7UYiSmmv0QazyrxoDIe306mLydR9dOCkPqVoCpqW0eETy106Ed5Z744pHc6yyFp')
,('13745678913','ae7faeb6e9e60cdc0cb0564403090e675bc05172b0975803c9dc6d004c4bd1b08184a7f31a2116d683b2fb30db825e290f4375837fb3457bd71bb907284fd91c','TuVIr41miPXJt865HzZp2gXiwfR2zNzin30cDJJ88611ngMcynlWrUKQ82qPmh74QkNU2M72GOLuIc6W8JOwIjQQayekbjQXroUyd0SP8GLUBjMiQRmwcOzf6XEuk0Fn')
,('13745678914','4aa6cf13b49187deff928e99de34f59f847576070683da568161892b1a1e1e63b5b9c15f23272d3dfd095f87f16dd9da410a3d10a78a231ffbe454cac2bddb66','PaBRkhMz6T5vRaLJa0gIHSa2YjEby1ml6mMlun6HDmPpDr3HAkdPDHHae5uUwf9IRcOU2mehqlhKb2RZIavBqMCo5v3BmzKFdpSXmYeUph6uW8Kazxpz2nfcXV0IQDvY')
,('13745678915','b68639124ccc56a6e4ef3958c4b052afa7623cf46ec13d3035ef44cfdbad03807af1cb48a97e84c0c4b0e2b0bc255c1c640556a1c0a4a5b59ec871c67e0bc160','zqTD0dmLpzD8QpWm86khFRQe3WDzy4QeZ7tnIN4AypLRc0zv36wyiGnl33hPQGFppwUo6phmC16C3RJm6UzMdiOKyOZ2U9wYZwf6qKGcd6MoUpGfKyFkBzE3BVT6qRhV')
,('13745678916','4e8645324fe6dd9dc80263f70c7d70de5598bd16068c0033a6b3cf9daf4e6842fc5bcf917b8dbbacb4786d407ef37f7c50bb716f79585d4b1d47cca13c773bfa','R2yklLBSXpxArqbzSTUlQwH39FuzOJ87klykb6EOkgzHyDqpcFQzBUDR6Pi2yFOtQypqSrbAvWzM4TOnQADy7ORJfpyLauyV72LliQCZS4FHDoy5H2ZBbrjpMWu2QBr3')
,('13745678917','94ed472b1d70a98dbfc91af579a8f153e405308619ccac91686009ad06dacef5e607965c1a3ca8fa8eb7d6a120c5255253a20cade2541eab0b3e8a17ddeff35f','4J7jhcBGOxhygdlbgLWYcNOZPCfoEvArGPdGa4b2BP7WGvWIulT2rIj5TQgnQ49Om5zKGjVerl00LfGdHIG1g7TT3DZviJAP8Jy7jqOPWwBzPIR0SumdQiZIAQd8XOOq')
,('13745678918','807f84cba0355d8cfba816d4ae4042c6b6389040a9d28ba1c62be21a65e64e239048cb7f883349b3ff8a87cb442ad84c1976973fd73cedb84284796f703157c2','N8h13T4vn5VhUFJxNCbvaRygLNE0Q6N8WoZYLh6M1YI95yKyvCZAMn0adcqa52iogzi2Q4yCXd52gRWyGREfMCfLEiN4toK8qMJGHIzzqbR1xckfAP6BxSXtlwe3yBXd')
,('13745678919','6c72bba1c955bfc9ebba3008ba1a9fd33eb5b5bf6f73e614a4d94f70d767d6b185a7aba0b4142d07bb464521c756c62438b54aaa22b69fc48418b20daa0c1814','MwJDa4ehICmhffHUrSCBkc1h4AOmVDpZqUiCUzXjT5EB7d2mvHGBG7RQOE42yIYLdUIEMwMAKyFzIvGoG8QcxTtipPouBcWBPzF3SF181wYHXXbzwfZPOwx78z5pHNmY')
,('13745678920','245a81b291b8b3a12edb1504ebcb0b27fa3bb6a584f8ddd38bead55f8a8a70ca377f5bdd9bfe35cbb9aa2510f2c60a56cf8818ed4a65eadeab39254a46b5e224','EPKOVkfI2ngizz0Afp0jTMXyyFxRD7g6hizT3134YVPOcHpA96k3TQKzU7Dt67VfAtf40SOqqX696FfXpeAbLCozzqTwI8lnjvam7nFTxe3zS6dhgNJ0n37fmM3fypu2')
,('13719326102','ebe3c12884c61a730c6f987de6e486d786444bc3fa794b8e712d1e86113ec2285a6e14f35b8f9144bf900926e3a840a4bf9f62dd0e8676d737ac266fc89ef670','R1ewpYeiQV3TJnl0e698pSvnMH9gwCRBVxhmCK56TwkiqTbkVnpNO8xY4vlpqEJ7dAXGo0Cm3CkauZVVE1l0dHWKVe0GuLkXOox1cNyb3Cig6w9XitT1j20zBt62INWj')
,('13775119722','c3e1c7add4b653126864a7d730c03bbb0064feb89428dad3ae52a1588ebe2db07dc8ae3d5dd690b73b4e6db35c8b6959f1dfa200d344c253130789cc8e25e909','m9WzqoQ2SG4EAYToEbctxMpfhlymbg7ikjtp36IM8mSGcQqgOuCfUb5pSuIuOhXlhIFzpdkOfjGCNpnATBqHQzhW2vTEu0lDgcREDP7cCRoiJrZJEe5zxekjUJZnpy4C')
;
drop table if exists Bill;

create table Bill
(
	id bigint primary key auto_increment
	,userId bigint 
	,parkingLotId int 
	,accountId bigint 
	
	,price decimal(10,4)
	#,billStartDate datetime
	#,billEndDate datetime
	,lastPayDate date not null  #用户最后的支付的截止日期
	,isPaid bit not null default 0

	,index userId_idx(userId)
	,index accountId_idx(accountId)
)engine=innodb auto_increment=1 charset='utf8';

insert into Bill (id,userId,parkingLotId,accountId,price,lastPayDate,isPaid)
values
(1,'13745678920',4,14,9.0110,'2017-09-30',0)
,(2,'13745678921',4,15,9.0110,'2018-01-31',0)
,(3,'13745678922',4,16,9.0110,'2017-09-30',1)
;

drop table if exists ParkingRecord;
create table ParkingRecord
(
	id bigint primary key auto_increment
	,lotId int not null
	,positionId bigint not null
	,accountId bigint not null
	,startTime datetime not null
	,endTime datetime
)engine=innodb auto_increment=1 charset='utf8';


call updateAllAccountState('2018-2-1 0:20:0',@tmp);
#call generateBill('2018-4-1 0:20:0',@tmp);