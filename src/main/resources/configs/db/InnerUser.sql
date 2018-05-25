create table if not exists InnerUser(
id int unique auto_increment not null,
nickname varchar(16) primary key not null,
password varchar(512) not null,
typeflag int(2) not null,
name varchar(16) null,
salt varchar(512) not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into InnerUser(nickname,password,typeflag,name,salt) values
('12345678910','f4f6bc7ef37ac21ef4d9b9a89c65ac2b00afc9e8e5ea262b28cdcb95aa732f4e3535e2fffe7f24e77b03034a9c7969f15a88f3b891a5c69c962f8f2840e0b0f5',0,'Python','D7gS3A0tTzKld4rW6cvWOtZBFPCGr4FHTD7W3JJ69HL6xEw7krLtKPK0XncVCTHgCPZxJ1nZdnImAqV4tnZlGjiQfE45f92aqDzLzeN98t3SMbBSKuwzYDzKMqZnahvq');

insert into InnerUser(nickname,password,typeflag,name,salt) values
('15219326102','806c47a29cfdb385f0fd75c25d8044dd6861af48b5490563c6e170d0e367f42f879e4360cf6dcba63dc13e1749344c088d493f5e8f8e8c9b0967c85a24ef40ab',1,'Java','K7mpfUS3hz1ATz0ftUILHyLZQGQy9oKKz4P6rCS93YkgbTGIIXB2oPwO5zF1wRktPny30gfz47cG9mMXw1vz10IDgz3aGz0tQvhRgnvkFjq43wzf5niz744tAduqhwh9');

insert into InnerUser(nickname,password,typeflag,name,salt) values
('13075119722','e3a6abab655f91888fb527411e931a1b45bfc2ddcc22de4d64d1118bfc47710019fd31ef6bd8ebf6ed9bba71bd35d0ed98a5a6ad613d9fbbb5d6376f4e6335cc',2,'Object-C','GSdOFMe9zJwt5wgygTgrMq2wIDrFHmHWz9USXYNJzdrzcGBVmd4FoCnuHZRIW9eYvkx7EMIKRiSZQP9rdwVKtKKlc2ZKUMZFlrfF3cnrSSpy9UHrZhHv9RmSHwUd5rE1');

