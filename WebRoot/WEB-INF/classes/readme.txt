1.�����
	1.1���뿪����
		mysql����
		beanUtils
		log4j����
		jstl������
		
	1.2������֯�����
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
	
	1.3ΪӦ�ô�����Ӧ��ͱ�
		�鿴���޸����ݿ��ַ���
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
	values('78','nyt','��','1993-12-24','18971491313','77@77.77','����,����','vip�ͻ�','һ������');
	
	
2.����ʵ��

3.дdao

4.дservice

5.дweb