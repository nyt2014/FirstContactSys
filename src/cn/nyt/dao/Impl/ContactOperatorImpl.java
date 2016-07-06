package cn.nyt.dao.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.nyt.Util.XMLUtil;
import cn.nyt.dao.ContactOperator;
import cn.nyt.entity.Contact;

public class ContactOperatorImpl implements ContactOperator {
	
	//存储联系人的xml文件的路径
	public static final String PATHNAME="f:/20160530_Contact.xml";
	/**
	 * 添加联系人
	 */
	
	public void addContact(Contact contact) {

		try {
			Document doc =null;
			Element rootElem=null;
			File file=new File(PATHNAME);
			
			//1.需要将contact对象保存到xml文件中
			 if(!file.exists()){
			//若是没有xml文件，则创建
			doc = DocumentHelper.createDocument();
			//创建跟标签
			rootElem = doc.addElement("contactList");
			 }else{
				 //若xml存在，则读取
				 doc = new SAXReader().read(file);
				 //读取根标签
				 rootElem=doc.getRootElement();
			 }
			
			//创建根标签的子标签
			Element contactElem = rootElem.addElement("contact");
			
			/**
			 * 由系统自动生成随机统一的ID值，并赋给联系人。
			 */
			String uuid = UUID.randomUUID().toString().replace("-", "");
			contactElem.addAttribute("id", uuid);//为子标签添加id属性
			
			//为子标签添加文本标签,并通过contact对象获取对应的文本内容
			contactElem.addElement("name").setText(contact.getName());
			contactElem.addElement("sex").setText(contact.getSex());
			contactElem.addElement("age").setText(contact.getAge()+"");//注意整型转成字符串
			contactElem.addElement("phone").setText(contact.getPhone());
			contactElem.addElement("email").setText(contact.getEmail());
			contactElem.addElement("qq").setText(contact.getQq());
			
			//把Document写出到xml文件
			XMLUtil.writeXmlDoc(doc,PATHNAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 修改联系人
	 */
	public void updateContact(Contact contact) {

		/**
		 * 修改id为2的联系人
		 * 1.查询id为2的联系人（使用xpath技术）
		 * 2.修改contact标签的内容
		 */
		
		try {
			//1.读取xml文件
			Document doc = new SAXReader().read(new File(PATHNAME));
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='"+contact.getId()+"']");
			
			//2.修改contactElem标签内容
			contactElem.element("name").setText(contact.getName());
			contactElem.element("sex").setText(contact.getSex());
			contactElem.element("age").setText(contact.getAge()+"");
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("email").setText(contact.getEmail());
			contactElem.element("qq").setText(contact.getQq());
			
			//3.将doc对象写出到xml文件
			XMLUtil.writeXmlDoc(doc, PATHNAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 * 删除联系人
	 */
	public void deleteContact(String id) {

		try {
			//1.读取xml文件
			Document doc = new SAXReader().read(new File(PATHNAME));
			//2.根据id查询出联系人对应的标签
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='"+id+"']");//注意xpath语法的书写
			//3.删除查询的这个标签
			if(contactElem!=null){
				contactElem.detach();
			}
			//4.将修改后的doc对象写回到xml文件
			XMLUtil.writeXmlDoc(doc, PATHNAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 查询所有联系人
	 */
	public List<Contact> findAll() {

		//2.创建List对象
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			//1.读取xml文件
			Document doc = new SAXReader().read(new File(PATHNAME));
			
			//3.读取contact标签
			List<Element> docNodes = (List<Element>)doc.selectNodes("//contact");
			for (Element element : docNodes) {
				//创建Contact对象
				Contact contact = new Contact();
				contact.setId(element.attributeValue("id"));
				contact.setAge(Integer.parseInt(element.elementText("age")));
				contact.setEmail(element.elementText("email"));
				contact.setName(element.elementText("name"));
				contact.setPhone(element.elementText("phone"));
				contact.setQq(element.elementText("qq"));
				contact.setSex(element.elementText("sex"));
				
				//将封装好的contact对象存入ArrayList中
				contacts.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return contacts;
	}

	/**
	 * 根据编号查询联系人
	 */
	public Contact findById(String id) {

		Contact c=null;
		//读取xml文档
		try {
			Document doc = new SAXReader().read(new File(PATHNAME));
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='"+id+"']");//使用xpath技术
			if(contactElem!=null){
				
				c=new Contact();
				c.setId(contactElem.attributeValue("id"));
				c.setName(contactElem.elementText("name"));
				c.setSex(contactElem.elementText("sex"));
				c.setAge(Integer.parseInt(contactElem.elementText("age")));
				c.setPhone(contactElem.elementText("phone"));
				c.setEmail(contactElem.elementText("email"));
				c.setQq(contactElem.elementText("qq"));
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return c;
	}

	/**
	 * true:重复
	 * false:不重复
	 */
	public boolean checkContact(String name) {

		try {
			Document doc =new SAXReader().read(new File(PATHNAME));
			Element nameElem=(Element)doc.selectSingleNode("//name[text()='"+name+"']");
			if(nameElem!=null){
				return true;
			}else{
				return false;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

}
