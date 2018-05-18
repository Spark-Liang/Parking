create table InnerUser(nickname varchar(16) primary key,not null,
password varchar(256) not null,
typeflag int(2) not null,
name varchar(16) null,
sex tinyint null,
phone int(11) null,
address varchar(255) null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;