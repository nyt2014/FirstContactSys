1.搭建环境
	1.1导入开发包
		mysql驱动
		beanUtils
		log4j开发
		jstl开发包
		
	1.2创建组织程序包
	cn.nyt.domian
	cn.nyt.dao
	cn.nyt.dao.Impl
	cn.nyt.service
	cn.nyt.service.Impl
	cn.nyt.web.controller
	cn.nyt.web.UI
	cn.nyt.utils
	cn.nyt.exception
	junit.test
	
	WEB-INF/jsp
	
	1.3为应用创建相应库和表
		查看和修改数据库字符集
		show variables like 'character%';
		set character_set_client=gbk;
		set character_set_results=gbk;
		
	
	create database day14_myCustomer character set utf8 collate utf8_general_ci;
	use day14_myCustomer;
	create table customer
	(
		id varchar(40) primary key,
		name varchar(40) not null,
		sex varchar(4) not null,
		birthday date,
		cellphone varchar(20),
		email varchar(40),
		preference varchar(255),
		type varchar(100) not null,
		description varchar(255)
	);
	
	
	create table customer
	(
		id varchar(40) ,
		name varchar(40) not null,
		sex varchar(4) not null,
		birthday date,
		cellphone varchar(20),
		email varchar(40),
		preference varchar(255),
		type varchar(100) not null,
		description varchar(255)
	);
	insert into customer(id,name,sex,birthday,cellphone,email,preference,type,description) 
	values('78','nyt','男','1993-12-24','18971491313','77@77.77','唱歌,跳舞','vip客户','一个好人');
	
	
2.创建实体

3.写dao

4.写service

5.写web