create table if not exists User(
   id bigint primary key auto_increment,
   userId bigint not null unique key,
   password varchar(512) not null,
   salt varchar(512) not null
)engine=innodb auto_increment=1 charset='utf8';

insert into User(userId,password) value(18826237366,"123456"),(18826237367,"123456");

insert into Account(userId,parkingLotId) value(18826237365,8612345);