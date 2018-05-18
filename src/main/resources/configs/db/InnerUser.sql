create table if not exists InnerUser(nickname varchar(16) primary key,
password varchar(256) not null,
typeflag int(2) not null,
name varchar(16) null,
sex tinyint null,
phone int(11) null,
address varchar(255) null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into InnerUser(nickname,password,typeflag,name,sex,phone,address) values
('bb','admin',0,'ff',1,13579,'asdfghjkl');

insert into InnerUser(nickname,password,typeflag,name,sex,phone,address) values
('man','123456',1,'ff',0,135790,'asdfghjkl');

insert into InnerUser(nickname,password,typeflag,name,sex,phone,address) values
('ope','123456',2,'ff',0,135790,'asdfghjkl');

