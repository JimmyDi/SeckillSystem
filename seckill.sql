Seckill sql

create database seckill;

use seckill;

create table seckill(
	seckill_id bigint not null auto_increment,
    name varchar(120) not null,
    number int not null,
    start_time timestamp not null,
    end_time timestamp not null default '1970-01-01 00:00:01',
    create_time timestamp not null default current_timestamp,
    primary key(seckill_id)
)engine = innodb auto_increment = 1000 default charset = utf8;


insert into seckill(name,number,start_time,end_time)
values
	('$200 get iphone 6',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
	('$160 get ipad',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
	('$1200 get mac book pro',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
	('$1400 get iMac',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');
    
    create table success_killed(
	seckill_id bigint not null,
    user_phone bigint not null,
    state tinyint not null default -1 comment 'state: -1:invalid 0:success 1:paid 2: sent',
    create_time timestamp not null,
    primary key(seckill_id,user_phone)
   )engine = innodb default charset = utf8;