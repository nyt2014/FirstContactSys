package cn.nyt.dao;

import java.util.List;

import cn.nyt.entity.Contact;
/**
 * 把dao功能（增删改查）都抽象出来，定义成接口
 * @author Administrator
 *
 */

public interface ContactOperator {

	//【1】添加联系人
	public void addContact(Contact contact);
	//【2】修改联系人
	public void updateContact(Contact contact);
//	【3】删除联系人
	public void deleteContact(String id);
//	【4】查询所有联系人
	public List<Contact> findAll();
	//5.根据编号查询联系人
	public Contact findById(String id);
	
	//根据姓名查询是否重复
	public boolean checkContact(String name);
}
