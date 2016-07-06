package cn.nyt.service.Impl;

import java.util.List;

import cn.nyt.dao.ContactOperator;
import cn.nyt.dao.Impl.ContactJDBCImpl;
import cn.nyt.dao.Impl.ContactOperatorImpl;
import cn.nyt.entity.Contact;
import cn.nyt.exception.NameRepeatException;
import cn.nyt.service.ContactService;

/**
 * 业务层
 * 处理项目中出现的逻辑
 * @author Administrator
 *
 */
public class ContactServiceImpl implements ContactService {

	ContactOperator dao=new ContactJDBCImpl();
	
	//检查联系人名字是否重复的功能
	private void checkContactName(Contact contact) throws NameRepeatException {
		if(dao.checkContact(contact.getName())){
			/**
			 * 若是业务层方法出现任何业务异常，则返回标记（自定义异常）到srvlet
			 */
			throw new NameRepeatException("姓名重复，请更改！");
		}
	}
	
	public void addContact(Contact contact) throws NameRepeatException {

		//执行业务逻辑判断
		checkContactName(contact);
		//若不重复
		dao.addContact(contact);
	}

	public void updateContact(Contact contact) throws NameRepeatException {

//		checkContactName(contact);
		dao.updateContact(contact);
	}

	public void deleteContact(String id) {

		dao.deleteContact(id);
	}

	public List<Contact> findAll() {
		return dao.findAll();
	}

	public Contact findById(String id) {
		return dao.findById(id);
	}

}
