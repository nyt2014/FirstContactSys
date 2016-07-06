package cn.nyt.service.Impl;

import java.util.List;

import cn.nyt.dao.ContactOperator;
import cn.nyt.dao.Impl.ContactJDBCImpl;
import cn.nyt.dao.Impl.ContactOperatorImpl;
import cn.nyt.entity.Contact;
import cn.nyt.exception.NameRepeatException;
import cn.nyt.service.ContactService;

/**
 * ҵ���
 * ������Ŀ�г��ֵ��߼�
 * @author Administrator
 *
 */
public class ContactServiceImpl implements ContactService {

	ContactOperator dao=new ContactJDBCImpl();
	
	//�����ϵ�������Ƿ��ظ��Ĺ���
	private void checkContactName(Contact contact) throws NameRepeatException {
		if(dao.checkContact(contact.getName())){
			/**
			 * ����ҵ��㷽�������κ�ҵ���쳣���򷵻ر�ǣ��Զ����쳣����srvlet
			 */
			throw new NameRepeatException("�����ظ�������ģ�");
		}
	}
	
	public void addContact(Contact contact) throws NameRepeatException {

		//ִ��ҵ���߼��ж�
		checkContactName(contact);
		//�����ظ�
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
