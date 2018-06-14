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
('13775119722','122a4bab97ba30cd5d63614253f16024167768d6dc892706bc5fb9427907782c0e232119756c4d50aa12e64fc0ac19f9f2f03a5823a9e06a423a8337915d8fc5','Xd31gBzzLnRlgZNh3Gmnq8NXNhXHovndij04px7hk6TPg1Yv0wXUNqaIxYtlGGOKHQ8nXuWDiXZBDTj0cCmYOa7hk0FractOrH3aIeqZG82zzHwPzePgFlRA9TdKctXX'),
('13745678921','be49c41354437a4f4af820a1dc8d22582eb711105310912f56a2c35ee0efcd7efec0f461fd812f10c617c811318ba005c2f41028200af38615d72057994fd4df','t2e0j8zadkajOFaS3KfW7lmMAqx4Un9MWGgDgmAUmS5W2hIWtMfPLBDY8am2htMRJaNCWMpIoe4MD8btAEmIOb88IcjWCUjmJUefgmPLiPKIaIZHxrdFigadUzeLaT3Z'),
('13745678910','5b9f35b2fc9fe1656597c522cf0c1ca658e1f63d7eebf976819892084044e24239b1033c1b0298fea17eca4200bd4378ef561e5f15f33b47c7678d6920e90f3b','Eu6ZOokzL8p7zIWwf5fwfKFfpaoIobKoqKwShOG8DiPe2FbHn2WGozJPpxvkzF5y8Wl056O1DCcFIj4g0xnehRiZtP76OoBHz7Kncu4SAS2V2NRGK3POGKn0v8YX33xg'),
('13745678911','cd5b19db4bed18270eb8772387864ab6bfc2b990bec837509ae519cbb675a6c4126bd80f5672117cad1578b085f54477b65e84ce2e37323384b0a86234090e7e','30q9ihf5rhKu0xObSwBpTqHzhSQ0mxhaIuhKZWPIdthgLRFB0P9UjrzzElKtKEnyGiVPO2nVFmPcybm1V4AzbjCHG3hl5vxlHBcDhbza42jvvqFIaGHDoM8IuMSktuYN'),
('13745678912','9f8d455a00b55d50b284fea485e9ddf77bcd45e43df1aa0459210d7eb45133e17bf985dca1a7da2384614c31906b00bf2ae243a6650b95bcf0a7cfeb755b16e8','YU33itAKv01BSQOthnfrHo2nq1wZvtPSUJ4cv45gg2uwxuzziwtqi3TSwDBEfoiFzQIKmHf7FUf8jlqki9ElOMi2V0MexSzMfXhHpqzBadTz3ufpavDHaGLHOZv7tkDo'),
('13745678913','bba854a8267f0e117b4dabaaea6aaee25fbc7bb0f851be43ad5eef0829aa3160c225c4f976fd8c65633ab6764449882d12cb9f80fc15d6070f274322a1922102','KnzZ0NmMOPoVh4egdV62cITy0OkSQ5eGLpOW4QfZzFTYYMV0WUfkz6guxhR4V8TMXcxykoBejBb7wTphHnDOtdYwa6W8zBcl4Y4gc3nnahp3wlakgYFZi23nSHjG1rwv'),
('13745678914','bbaf721a17b914052a2de3b1605163a62811169f73e2a4782f0ff8da57426f81b376c068247f0faf87741cfc4970e5df1e754698e170e215b10b8d3c57e4cb9e','LqXqivPOSvNBdZ9l9vEvkCT2gqKOfVfCj36u45AG9GQF0cdHbEzCFzBUa32lR2OHo4DNYq62WxQPAUh1fYz09oZOwF12kSomz3wTbAxIGCYqcVdKpqERCMN4Y7GPi0Fw'),
('13745678915','69403daacc92a044525559b40ae1906415925e851163dda7d993f1c99e0b62d22f13c1deebf754db1f867c8c346f3272b8471ebcc2e11c04dbe1d00848d0fa4c','TLPVkPjDECVdugTVHONHQDharnt46VIxAX1xu82ShOzQe1DzPZ6Ph1jPZ657b2fb0tfagQRCQgG0oBgZTRSWyHlPtSmoKjHiRKyeIrFxR1EB2X632ZtzXpHPcgEqJeQL'),
('13745678916','c24fe6221c70d15df8e58f7ad2fb61b8b834ea73c263af24dae4c77dfec0596697ac6f2a5720156f9c895199d6bf72dff68dfdde1b5b116933887d1ad5881c87','pNmpL6WoXe7CRV7WhG4Gthg7cXrww34rPR66i0juD4FLaiIBgkTnzUnpE5oeaeINBcCEJrmWbZ4nKPKGUfro05VUi3N2nVf3YupQoEAHA2UR0Sld0alLwvomd1BlRRbE'),
('13745678917','dfde2eed9d195d10acc6caf91bcb195f24c6dac11b4113b3f01e5a75cf2fa0248e96571e3501524de3547ed77afb0a1df35589cdbb754c746a6b7472f1e6d03d','ZweRziX9qgmNpMtRdGMRTEeHq4GcoJVpQlGpqoyVALeJQ3praXcceEzhNKZtJOmXGzTZ4wqnZTZ8d09wUa57njf3YAFGrI4ha8OwNBHZeeo6eZxuTIynBBDqV1bL4NLr'),
('13745678918','953f1e8b9c0d0b4ce43ff28e1a0ec9a946df302c1b9864ff358dec0ad8a970227c44cf6b3051c49d4cc3eeefa4c12022cca6cc0bc6fd87653f068258b534aaf1','YSh09V1qWY0K2oHIidezxGXh7yRbiicU9E58Ne0ZFcq72B4p9m38a5vAinjx4je62Uz3Cmghjw4zxN1aNaioAkHTqJM60W1DolPafTz3M9gaaP1NeNmvOwzmwawnh8zi'),
('13745678919','a417921a9d49133acdd8f1910813cdedccd026ddb484bcc46f186e5c2287971512174a8011fe7ff3af607bc9819bddcfdd3302c9f54b2b50ea46b5c319f03f2d','8WnMi7ghKtbC945UoWSSoFDB00OTrV1GzUxWrrlEjbvDpHFiXbko5paK54Setzk0LT8OwUC12Bw0mbHUAArZ8RxmgmQCzRNpw8zCCSlFQPRVbuVb64bkdEtr1e2g8Hfz'),
('13745678920','40ca823d06a5f21f7cb85906046a1749978bb02df7f86c14d2762afbc65159e01cca0708748940a1446842fcff91bf9880e286f33f3b2c2f5c3ae7df25e2b201','Im2Nd4Y0eoVP9Re0iPrMrlwUZyfZy8OaDz7VvdcwwkWtTMMJrdU730uBLfQihWbngLh7txPfFrtyw8mh8j2eGQfvDgdNOgBoRG19Z3EmTtpCCw7v8rywMoQArDmdOU1V'),
('13719326102','a9722e12d3b7cd5b92df6b5b60554c53d3c74aa065d65523bbb13b9c0d5e979f15a604a1493f0abf07586c54c867a3af4eba6740cbac18d5cded8607b38acc7f','GXtoujTNmkw9yJ8EViRKjun0U9jGdBxkVn2gbtLINKXxnfgZdbnyEdCJm8wlhOqZtoSuIUFeyhS7QAj3CzODbOBd3dMf5KCb5W9bAlVYN2tFlqJ8GTQSzoeBcErN1Wtl'),
('13745678923','31cf6814552027b3899e118c88759d8be6e1dfbaad9acce60089400c14bd0809e4fb28741f9bc8dc0090efecf878f615bd470c7d442ac33290657ddd2cd54c7d','yWg9OauIILOz8hz17yfyo7AHP0GUIZ6ea00hp7wy0UExRQ87l6KRq2y0ckl3WDkYlScZ7ll4gMH8zanwUAg4w8ZWo8IzUzLAJDvPX8kgQeEuirzILyujiRRJz8huVNpj')
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