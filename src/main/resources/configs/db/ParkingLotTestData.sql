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
,(4,null,1),(4,15,1),(4,16,1),(4,17,0),(4,null,0),(4,null,0),(4,null,0),(4,null,0),(4,null,0),(4,null,0)
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
	,isParking bit not null default 0
	
	,currentParkingRecId bigint  #此字段只用于实现统计停车次数功能
	,currentStateLogId bigint  #此字段只用于记录Account的State状态变化
	
	,index userId_idx(userId)
	,index parkingLotId_idx(parkingLotId)
)engine=innodb auto_increment=1 charset='utf8';

insert into Account(id,userId,parkingLotId,parkingPositionId,cardId
					,price,state
                    ,currentStateLogId)
values
 (1 ,'13745678910',1,1 ,'0000001',10,0,1 )
,(2 ,'13745678911',1,2 ,'0000002',10,0,2 )
,(3 ,'13745678912',1,3 ,'0000003',10,0,3 )
,(4 ,'13745678913',1,4 ,'0000004',10,0,4 )
,(5 ,'13745678914',2,5 ,'0000005',20,0,5 )
,(6 ,'13745678915',2,6 ,'0000006',20,0,6 )
,(7 ,'13745678916',2,7 ,'0000007',20,0,7 )
,(8 ,'13745678917',3,8 ,'0000008',30,0,8 )
,(9 ,'13745678918',3,9 ,'0000009',30,0,9 )
,(10,'13745678919',3,10,'0000010',30,0,10)
,(11,'13745678920',3,11,'0000011',30,0,11)
,(12,'13719326102',3,12,'0000012',30,0,12)
,(13,'13775119722',3,13,'0000013',30,0,13)
,(14,'13745678920',4,null,'0000014',10,-1,16)#已欠费两季度账号
,(15,'13745678921',4,15,'0000015',10,0,18)#准备欠费的账户 ，当执行updateAllAccountState('2018-02-01 00:20:00',@tmp)变为欠费
,(16,'13745678922',4,16,'0000016',10,0,23)#欠费再支付之后的账号
,(17,'13745678923',4,17,'0000017',10,0,28)#201707开卡，201710欠费，201711续费正常使用到20180331
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
#已欠费两季度账号
,(14,14,0,'2017-07-10 8:10:00','2017-09-01 0:20:00',1)
,(15,14,0,'2017-09-01 0:20:00','2017-10-01 0:20:00',2)
,(16,14,-1,'2017-10-01 0:20:00',null,null)
#准备欠费的账户
,(17,15,0,'2017-10-10 0:20:00','2018-01-01 0:20:00',3)
,(18,15,0,'2018-01-01 0:20:00',null,null)
#欠费再支付的账号
,(19,16,0,'2017-07-10 8:10:00','2017-09-01 0:20:00',4)
,(20,16,0,'2017-09-01 0:20:00','2017-10-01 0:20:00',5)
,(21,16,-1,'2017-10-01 0:20:00','2017-10-15 8:10:00',null)
,(22,16,0,'2017-10-15 8:10:00','2018-01-01 0:20:00',5)
,(23,16,0,'2018-01-01 0:20:00',null,null)
#201707开卡，201710欠费，201711续费正常使用到20180331
,(24,17,0,'2017-07-10 8:10:00','2017-09-01 0:20:00',6)
,(25,17,0,'2017-09-01 0:20:00','2017-10-01 0:20:00',7)
,(26,17,-1,'2017-10-01 0:20:00','2017-11-01 8:20:00',null)
,(27,17,0,'2017-11-01 8:20:00','2018-01-01 0:20:00',7)
,(28,17,0,'2018-01-01 0:20:00',null,null)
;



drop table if exists User;
create table if not exists User(
   id bigint primary key auto_increment,
   userId bigint not null unique key,
   password varchar(512) not null,
   salt varchar(512) not null
)engine=innodb auto_increment=1 charset='utf8';
insert into User(userId,password,salt) values 
('13719326102','6601d6db8184eec086a647bdf96eacf66575dc85f53913a40451aea286c699a8a8bf32a0d1059a61647d19316069c8f380d29ce4948e9d60eed867de8ca7ad4d','lD80uo1JxiTqbr99TocnNhDDvc8MYZ5tn7OODclXlDpydPA8OoZNKiO2MDLp7BXGPjrxLJhAqH7dYNYLTJLJDkFyuV3SuMbwK6EKxNA4kE8otGvf1X6zKHjV4qo0jS4p'),
('13775119722','c186d41d6ecac8293e07d0be0c5b78de0876685a302cc2567c8f928bfc4d6d2da9698c0b5ad6b0f59d1d7a682c22ff0cb4b106a0f581c40327e30428f8e9bbf6','e4Rdz4izJZg4Dc6Nmg04RexgY52tVv4FtkXBMk61fgAKfQpjO9ArFNWGNQfFCmlpMFcgMl4ikPkyMXqIJ5CjJJZjGDUoyfIrlQiIiX7MFqAulTH7FiKv4hYHWgALgLUm'),
('13745678910','9d2d191a63de4103abd296522206aec42ace7039b650f93e25392489ab9a430acda5710bce2a355df7f300be72cd36736a3328b86a80dc3d42dd3df31d303036','ZPzwzv5EHSNtwNNoZNv7vOj5Yi3DvpzNfA5oMLyEMHFl0JIoawPlcDwfdhFppWW5bnAGre5gB66iX3Ph7L5PYQnC0mfS6Oc6AUthzFhfKzOuV0uJy8qlHvtVL5ARArZ1'),
('13745678911','65a5a3bc1d228b0e8f00da084e0a1a1537ff48898b34f3d2ef0026160bc3c33b081667bbabf06069edc707ba715fdd28fdf9b2b03983f5a9b0a888ea080312b2','ueONyrdKb9WW90nT4IHIUzk4EoBDzHHOhCYzt1eYNkTunVUINEeQoro2V3CPlELkfnp5Kmz3MxiRCykQGWyy3G5iuPNlDoS8VaMUIMuOMCVfLQfLGEhmXTM1bv8F5JqO'),
('13745678912','117c89598aed4119f69d670542cb167b1d4cbaaa58bf5a51c1e2dab0d73cbc548b4232068624da4b7e7f87ec4e657ae4b51930eefd5bc0e741ca1fbef0910621','oEr5hKZ9Xn2WDal6VrZirxTi7w7lPKf0ftFwpXdctJJjGfCD6nxXieW2yZvQDO6IRBqCpXNvzRHGzNNxfcWPtpa9JeJCqgDkAm61KDvi5nfAcnM646hlISpok19OkSFq'),
('13745678913','9fa6249790d2af17eeb164da41d1874fa86e90baca4041feaca3d51d7b7fb820692c975e5df5c4705e7d4ecd0fd51a7c61d62a0ba90e5bfddbbc767fe0a7fac0','LYRxCK5ay3MBM5VSZ7vKpjXvu8ie9dSiKKJueVF2VRXwFgcvl55RzFAbbJteuBc0g3k9ymO98THy8uAufrkZ0QeMhIAIoAXFru9n7DzA3wfdxcPkQb5iCaNnn5h6hdWF'),
('13745678914','9956aa6dc65ea58a09b3ddcd91178e4e885bf89bdabaae38892b3605f2e01a14eff6209123b9021666a2584bee72fc55e26139d5d2418510ccd42af9f707b5f5','6ZdyUzmvqnXyPdounahKH3r4XX3xzD8BJCd0LR8LlJllz587ACGKawTuxNzjfrkaUU3qwCllZuilzbA6AXqTXp7m7HW8c7zTz0PWkBbL3UJX406dk8o2fePiQwMKCMMu'),
('13745678915','6d6d3089b96610bcd062631e24af95af6ee598160dbbbca44574841da09ff4c73490d5504b7c1334cc9441e5d07291591828f418e339e17a3a0b1d26fdfccbee','dOrrYVLxqIGlgrNzeZmrTBDjRZqKo9gUyPB5i61mtxieyw9kJtV8PmkC48yRIYSG4Q5Bukne8l0GO7UVeVpCVzw4TAYCAKnzEP9tG2C2oINo4qaOPS0zOOKU5YicZ4MI'),
('13745678916','668f47c484c193606c29b5900de243878ef44b0f9aab38c170184686778f12236abaea1f4c016030e567ad2099fc29675efc0e29486636c473411db9d2f64ac1','9SE3MMqlVWcR9fpWamO6zJacKiHt3y6hyjhQ3W3Q4EjPWlVYC10qaTookOWo0tfoqVTpCL6FIIlMrqw2DwjaCZoqd6OU8PAlttDclt5deBX4GHEcOEBz6fNzfHphz4Ak'),
('13745678917','0c4d2ebea0d3dc4dbb2bcc9a8a111e8ba71b3dbff939be534dc2f7ada63dcd13b7542c3de6325d66fb805a685b368d5f8c7d9eb8a51b185d1d15f4c024f1bc8c','VxyGReMe3RRmDRxw4KA9xFidVVl4TzCmBoNDjDZqSf7VP4Nazy03hcbuqpXERBdf65r6ovAuMOVqrGVYzlJzWnLdiRMfojRfZrIEn7EH6hthZHNowjXIcLlfFGicn5up'),
('13745678918','3bc6654daf8f7d1ef10ca65a9e51b922e49169ec99f7cdb549f4cbf6bab09f521f0b67b1e2a1b74c2e54deefb58e0f00841b8d1862a79a8515df6df27f83693f','nopFlj4AjcIpO4IW5JSPFVY4arwhegQ6xlCi6gxENInL1VXToqkxydJTqvucpy6pa4poXYh22lSHXNNwBK7ShWU1SKUOKYmSxlEiu3OVGzmDzGxC2X9Nv3rKuDx1p3XI'),
('13745678919','054382ff7a9f2ac47df58662c1301eb0c008bd9aa35709236bb7d1985b48b20995ee41eaa42bf9e11e8bf338084e1990bf1e4b7de9182238e58eef0cf48fc721','LgxfWaZSEOJO5orFlXWQicFhEP6to96QGFvObhyITdiPV43679QbIbMTM1TVzTyRAXYI4G5GAbKwDg4LwJUVSSBbANeUxCh5SoHNjUY5VDOzI4opaUSJZt4rv38JD1Zf'),
('13745678920','f812928919ce057fd0d2578d8e8afff6c8f37b4ef6acf81cf8480a673d7455b229461c62c4c6f1df25a3364f6cfd206a5af1896ce20655015031ac17f53809c0','dGNUvy8F49XzjX34A872lRoWVVlnT1LWS8SZBIJOcP979RbGdMfBKH8hNqzZBiJ3UJUh0261lvkrJVn3hdoruGQvEUTrnuXzXc7GA0qlfx6IPEbCmY0mLI6q6eTvgBbc'),
('13745678921','0442b77a7ee54dbd5f31fba3ce8c3bbe65ff2a2407baa27a961b601fb9b96c8ca736dd9834d6f39b754931938aab46e6996f6efb5093c474434626d490929630','uqUXRO773BYNLaRJUgQ0zgWh9yjOEwdQO3QpI8dAbpQeMOmnjz2EYL83QkcKewWg928dtHnZzwKMNilJvPcBjFbRhxTjQJN8q3k50di6Hm6rCoVUEbBkMKjKjoXnhItB'),
('13745678922','6703b0c97a2c59ac4c865e7aeb2b211db46278dd44f023be1589e95609603db47f3146137af23333d57277d04ed977c434a75295bcade51ca214900aea99e422','9pZETR6ucrCGSSzqAK1yCe6pq5brucgmSrmYcEdk6qBA2FVrNhgekbYtu4wvpBpiMml5qIkI1aGZ0ueDoJon0hTluCmkr9kJffzSAhVwZbRnp1jjlVIw8tM3mRqggGzS'),
('13745678923','5412584c3edbbe75d42c5be58b3457e24bffcff6dea00f561347e5c1a562e3de4feba7f71a542c8db3c38830c09b2dab024e64d14a95f991037835c18dca0841','Y0ZIow4uOlp68uxdjIaWF1HESyU39YYA286ScVuH33B0joLxX4TG7xFUlJ0fkLti0jQcBH5IQap9VXlBg4ekCZYVEyzloVoGqBaRkU3XUfSyKd93ahaDoNC7AQEGJKk1')
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
	,lastPayDate date  #用户最后的支付的截止日期
	,isPaid bit not null default 0

	,index userId_idx(userId)
	,index accountId_idx(accountId)
)engine=innodb auto_increment=1 charset='utf8';

insert into Bill (id,userId,parkingLotId,accountId,price,lastPayDate,isPaid)
values
(1,'13745678920',4,14,9.0110,'2017-09-30',0)
,(2,'13745678920',4,14,9.0110,null,0)
,(3,'13745678921',4,15,9.0110,'2018-01-31',0)
,(4,'13745678922',4,16,9.0110,'2017-09-30',1)
,(5,'13745678922',4,16,9.0110,'2018-01-31',1)
,(6,'13745678923',4,17,9.0110,'2017-09-30',1)
,(7,'13745678923',4,17,9.0110,'2018-01-31',1)
;

drop table if exists CurrentBillMap;
create table CurrentBillMap
(
	id bigint primary key auto_increment
	,accountId bigint not null
	,currentBillId bigint not null
	
	,index accountId_idx(accountId)
)engine=innodb auto_increment=1 charset='utf8'
;

insert into CurrentBillMap (accountId,currentBillId)
values
 (14,1)
,(14,2)
,(15,3)
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
call generateBill('2018-4-1 0:20:0',@tmp);select @tmp;