create table if not exists User(
   id bigint primary key auto_increment,
   userId bigint not null unique key,
   password varchar(512) not null,
   salt varchar(512) not null
)engine=innodb auto_increment=1 charset='utf8';

insert into User(userId,password,salt) value

(18826237366,'f4f6bc7ef37ac21ef4d9b9a89c65ac2b00afc9e8e5ea262b28cdcb95aa732f4e3535e2fff

e7f24e77b03034a9c7969f15a88f3b891a5c69c962f8f2840e0b0f5','D7gS3A0tTzKld4rW6cvWOtZBFPCGr

4FHTD7W3JJ69HL6xEw7krLtKPK0XncVCTHgCPZxJ1nZdnImAqV4tnZlGjiQfE45f92aqDzLzeN98t3SMbBSKuwz

YDzKMqZnahvq');

insert into User(userId,password,salt) values

(13760681812,'806c47a29cfdb385f0fd75c25d8044dd6861af48b5490563c6e170d0e367f42f879e4360c

f6dcba63dc13e1749344c088d493f5e8f8e8c9b0967c85a24ef40ab','K7mpfUS3hz1ATz0ftUILHyLZQGQy9

oKKz4P6rCS93YkgbTGIIXB2oPwO5zF1wRktPny30gfz47cG9mMXw1vz10IDgz3aGz0tQvhRgnvkFjq43wzf5niz

744tAduqhwh9');

insert into User(userId,password,salt) values

(18826237367,'e3a6abab655f91888fb527411e931a1b45bfc2ddcc22de4d64d1118bfc47710019fd31ef6

bd8ebf6ed9bba71bd35d0ed98a5a6ad613d9fbbb5d6376f4e6335cc','GSdOFMe9zJwt5wgygTgrMq2wIDrFH

mHWz9USXYNJzdrzcGBVmd4FoCnuHZRIW9eYvkx7EMIKRiSZQP9rdwVKtKKlc2ZKUMZFlrfF3cnrSSpy9UHrZhHv

9RmSHwUd5rE1');
 