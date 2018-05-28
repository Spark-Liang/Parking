use train_db;

#global drop table

drop table if exists Bill;
drop table if exists Account;
drop table if exists User;
drop table if exists ParkingPosition;
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
	,parkingLotId int not null
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
	,userId bigint 
	,parkingLotId int 
	,parkingPositionId bigint 
	,cardId bigint unique key 
	
	,state tinyint default 0
	,isParking bit not null default 0
	,currentBillId bigint
	
	,index userId_idx(userId)
	,index parkingLotId_idx(parkingLotId)
)engine=innodb auto_increment=1 charset='utf8';


insert into Account(userId,parkingLotId,parkingPositionId,cardId)
values
 (1 ,1,1 ,1)
,(2 ,1,2 ,2)
,(3 ,1,3 ,3)
,(4 ,1,4 ,4)
,(5 ,2,5 ,5)
,(6 ,2,6 ,6)
,(7 ,2,7 ,7)
,(8 ,3,8 ,8)
,(9 ,3,9 ,9)
,(10,3,10,10)
;





drop table if exists User;
create table if not exists User(
   id bigint primary key auto_increment,
   userId bigint not null unique key,
   password varchar(512) not null,
   salt varchar(512) not null
)engine=innodb auto_increment=1 charset='utf8';
insert into User(userId,password,salt) values
('12345678910','376792bd347f531b0bd25f24d9a524777466724d9c1fac62d18ec9ac179b6f76876fbcf63d1c8869d828db5430d4818eadc3136bf55849c6578ab7d318c21912','zEMyr5UaS6EyQzE1Ly3ptB4jUGZBJakSMMoFFv0TBj3oVjgEYjeUeoWrXPcfyj79AjvIS3Dh21kWjhcBmjdnrBGNvrKrJn4rLZPNvWMqzDVvkPDfeP26VtTieb3NVAkW')
,('12345678911','ebcc9ac561dd1e9ffe9feedd015e6d3c8c1f1625d00fd17da822ff2b69fe54c0e04082306ef7210a29c4ecfc844b9f6acffaa84751eb51d55b243f296f41d708','083AwPHzNubuyd2od6ta9FHoIzYvXtmEB2vBE1xQduDpnrTg4o3Dyg0RbniUHzPiUFBzdfW1fdMBrx9xpcOvyOR21Okg7IUpVXyjyt2VyrykN9EGdzEz2xgawehQM7mP')
,('12345678912','70dddd1219b6cc6ed8188c804db6ded955d775e54f26ceca643f4b6c5d468cfa2c7fa7cf425dc0de76db09842bf39f0ab43f5df988e1045322cfeb212007464b','4HRFRzJWcYDT9QCZ3zRODz9CYWStOcfJRurw8ZPq9nlKSUPwQzgSUTu71vdtbVr7FRjN5T7aO4rYS4UUH7fCkfqLWW8gdtFjPKPTuILbHX4hjpoh5mUceH3DlPgG63Vd')
,('12345678913','e41762216c0c01ea2e322ea4c9ccfcb1bf8fde04347eb9829e30cbedf58ecea1e7664ef292b0cad5aac728d4ad301b862c00033773e10207c3dbb94ef837f46c','i9CkCnGho1EIAR0Z7MSvg0NI8AaUna5tgCWb87JLomDZggfrOx6VH70HzhmguLVN8ZzpldMGiOzn3NlYpYQHH3KUTZi2dBL4KtPXbVG7jJYdbLV9vMtD3PTOEq8Kh69i')
,('12345678914','359695c09d67063f606faa734ac63d7d8254c6dae1abe5dda9c828585d59e19fc5ae6aecaf89c82ab8797ad7d13895a5477855ad9d59a343f619f738e522d66a','zVaAAMXWdVFdYSXZTVhpHARIUeI0Ytfg23UjqbWEdvbbMQDh4ZclTBi9cz1Q9vuwf3SghGmPMCwmSE6ntb3WL5Aa5vDu2vexQxWigCALP6qxxjWvxKYeCtpYKGC581uR')
,('12345678915','eb46901a22b42964391d4401c363e8171eba1be9531e02aa35d5cec182e4047850f6cc33d64c4cfa72110b129f117e1d9cae50b4c161954d96e5ea2cf39a3788','9Z2cwbPNgnKvukhuFCdULabypZo7TZmVDIMMnzjxvqE0J0khZvmNdbmySM3vYgzQ1IPvhRH6J94zcm3jUgjWpDU2IzRLELiYRPrTZo0HXAzm5Qb0oiWWUHFM17SjJN63')
,('12345678916','8d660410652af57e9434819dc52b9763fafaa1267f0ca4643c1afcf46ed5821be036b2cdd28d8793a90ff0275a5bd207e9b172e40119c147770eca6c69bd07b7','dHASprdw2O1TwZHhDnX1mEVg3nTEzf5wg013l2UjRmUidw5n2ay0FpPmOl7NND2LzpQMVw2aqxQueFVt2OfXExmzUuvjtnNfkuphArmRQVtNGJww63J1CvqqTOdjEuoa')
,('12345678917','f5f5ed9ed78ba0e5fa83d7cb44a12a1c4a0577407c2c617573dbc1130a15f3d448b5ee3c5f8d5e2ab3e10f6e650cd7c93352337882c1a88895a0a6e40716d5b9','vOojeMekyL9f6FfzRCA3Vifzbmum0WQ7rCmHqfdzE3X6LilMaS8nqQPO0TK5RdLDD1w9Q6RW2xRPBXNo0PDmQ1cUeR4uC0kXE7HPp3NreEBGYa7M0cBpbJwrnIOJ1FHn')
,('12345678918','0baa29e94770d3182f06a8ffc9de106040a679f7c9f507130b4f60068dcce2c7209d81d7755bdc9a72a240b3164066195be353d6b77205a33b2992dedbf3be1b','qzdKxqxFC8v9brnl97wGLbNcqgJSbA96QbYiBqvCufFWG6nkTd8Dlrhy4VxteZogPzEJ6f0Q3qHTxvJCneNOvyWz7NCRMNjzOEJz42LYedS6bAzMHz0vHUHXC0Mc7zWX')
,('12345678919','1f687b8762d0557c81b0a68031604bbc63d9a1a9cfc6bbe9975111da0f5f7dbcbf01ea400e0efd44ff512f27161d36ba58d833e62b5edc63f616b83ae91ea16f','bxZMMVlMQmYEDVAixgw0TB22uFCHAzUcOnfojAxZ1WUZ5XFzK33QRkj7SRhc4wlThA83QgDVtcZwWClxrJv4bLdgbyEG3IVkM7L9glxqlyZmkh58MOQ6beDmfS1AzQA2')
;

drop table if exists Bill;

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



