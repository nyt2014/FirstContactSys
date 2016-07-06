package cn.nyt.service;

import java.util.List;

import cn.nyt.entity.Contact;
import cn.nyt.exception.NameRepeatException;

public interface ContactService {

	//【1】添加联系人
		public void addContact(Contact contact) throws NameRepeatException;
		//【2】修改联系人
		public void updateContact(Contact contact) throws NameRepeatException;
//		【3】删除联系人
		public void deleteContact(String id);
//		【4】查询所有联系人
		public List<Contact> findAll();
		//5.根据编号查询联系人
		public Contact findById(String id);
}
