create table if not exists InnerUser(
id int unique auto_increment not null,
nickname varchar(16) primary key not null,
password varchar(512) not null,
typeflag int(2) not null,
name varchar(16) null,
salt varchar(512) not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into InnerUser(nickname,password,typeflag,name,salt) values
('12345678910','123456',0,'ff','jfaoidsfjasofjadsop');

insert into InnerUser(nickname,password,typeflag,name,salt) values
('15219326102','123456',1,'ff','ierjawjfoisjJjjfaB8');

insert into InnerUser(nickname,password,typeflag,name,salt) values
('13075119722','123456',2,'ff','mbdfbMjfiaTGAF960ee');

