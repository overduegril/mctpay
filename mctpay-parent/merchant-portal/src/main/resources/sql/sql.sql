--控制商户email  登录名  手机号 唯一
ALTER TABLE `merchant_user` ADD UNIQUE (`email`);

ALTER TABLE `merchant_user` ADD UNIQUE (`login_name`);

ALTER TABLE `merchant_user` ADD UNIQUE (`phone_number`);

--会员等级

create table member_grade(
   id VARCHAR(32)  not null comment 'id主键',
	   `name` varchar(32)  not null comment '等级名字',
	 points bigint(11)  not null DEFAULT 0 COMMENT'对应积分',
	 merchant_id varchar(32)  not null COMMENT '所属商户',
	 discount_rate DOUBLE  comment '折扣比例',
	 create_time datetime   not null comment '创建时间',
	 update_time datetime  not null comment '修改时间',
	 create_user_id varchar(32) not null comment '创建人',
	 last_update_user_id varchar(32) not null comment '最后修改人',
	 statu int  not null default 2 comment '状态 2为激活 -1为冻结'
)