create table if not exists InnerUser(
id int unique auto_increment not null,
nickname varchar(16) primary key not null,
password varchar(512) not null,
typeflag int(2) not null,
name varchar(16) null,
salt varchar(512) not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into InnerUser(nickname,password,typeflag,name,salt) values
('12345678910','9a0d693aee1544c1bf4a02f660295f73ea5824b3cc5c8914fbb6aa6ad905cfcfd91be2fbfe5c0efba9175663da4443e91f717d749d1b73ec8f05f0b85b586a61',0,'ff','PofarU8QoiTpr4BjVO1oG7v2IVzCSVXOTGb7Ohyrz2xe1WtT6oj6vMwc4g9T5NI85CPjgm2CZkW4jKhgR0bkrat09kIRfdknacre133VmIIiufigRN7cr7X8fuTXinHw');

insert into InnerUser(nickname,password,typeflag,name,salt) values
('15219326102','f7e7b8ae72e1165fec7c8624534f9ffc4012913b1f7b4a4ebc2f354e38b6c4dfd5e0892736d4a91949e3b264c60c7d0777e6a7de05a3b921cf195bf2b1e4ae83',1,'ff','N9gTqltiVdyCK9gWfMbyVCHJjGC1ziHwBVPRU8DZtg9NJwf5rNSAacUq9iML3V5QA0RhNQ4bEn4wKuO8kJbX3Ez6CgYbhltkr6cRaUiTNRWaNpRiVpv94cxK6P7LILQO');

insert into InnerUser(nickname,password,typeflag,name,salt) values
('13075119722','5ce6f667bc728e568c0b1a0af538ee27a06b872536032f2624e8f68eddc403a898cd69437c88cdd2d19ba5ce1dff9d170f36835f856ac884bfb3d78ab5359642',2,'ff','L5fIVijGNeGUzGpmMc51lIO5QZ34UN46wPwmhBPrMamygFHzQznvm7DmPW3daL3wDb5gOvhz80fB2fiftOc5Ig5zoSLFjKhJHLkP6HEhXiSvZD9OccXzT57juJIE2ZDt');

