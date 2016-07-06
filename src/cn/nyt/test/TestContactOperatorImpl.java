package cn.nyt.Test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.nyt.dao.Impl.ContactOperatorImpl;
import cn.nyt.entity.Contact;

public class TestContactOperatorImpl {

	ContactOperatorImpl operator=null;
	@Before
	public void init(){
		 operator = new ContactOperatorImpl();
	}
	
	@Test
	public void testAddContact(){
		Contact contact=new Contact();
		
		contact.setAge(22);
		contact.setEmail("999@qq.com");
		contact.setName("wqwq");
		contact.setPhone("18971491313");
		contact.setQq("999");
		contact.setSex("男");
		//传入operator对象
		operator.addContact(contact);
	}

	@Test
	public void testUpdateContact(){
		Contact contact=new Contact();
		
		contact.setAge(21);
		contact.setEmail("820753666@qq.com");
		contact.setId("3");
		contact.setName("wzqf");
		contact.setPhone("18971496666");
		contact.setQq("820753666");
		contact.setSex("女");
		//将修改的contact对象传入operator对象
		operator.updateContact(contact);
	}
	
	
	@Test
	public void testDeleteContact(){
		operator.deleteContact("3");
	}
	@Test
	public void testFindAll(){
		List<Contact> list = operator.findAll();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
	
	
}
