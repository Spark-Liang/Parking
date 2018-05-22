create table if not exists InnerUser(nickname varchar(16) primary key,
password varchar(256) not null,
typeflag int(2) not null,
name varchar(16) null,
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into InnerUser(nickname,password,typeflag,name) values
('12345678910','123456',0,'ff');

insert into InnerUser(nickname,password,typeflag,name) values
('15219326102','123456',1,'ff');

insert into InnerUser(nickname,password,typeflag,name) values
('13075119722','123456',2,'ff');

