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
	
	//�洢��ϵ�˵�xml�ļ���·��
	public static final String PATHNAME="f:/20160530_Contact.xml";
	/**
	 * �����ϵ��
	 */
	
	public void addContact(Contact contact) {

		try {
			Document doc =null;
			Element rootElem=null;
			File file=new File(PATHNAME);
			
			//1.��Ҫ��contact���󱣴浽xml�ļ���
			 if(!file.exists()){
			//����û��xml�ļ����򴴽�
			doc = DocumentHelper.createDocument();
			//��������ǩ
			rootElem = doc.addElement("contactList");
			 }else{
				 //��xml���ڣ����ȡ
				 doc = new SAXReader().read(file);
				 //��ȡ����ǩ
				 rootElem=doc.getRootElement();
			 }
			
			//��������ǩ���ӱ�ǩ
			Element contactElem = rootElem.addElement("contact");
			
			/**
			 * ��ϵͳ�Զ��������ͳһ��IDֵ����������ϵ�ˡ�
			 */
			String uuid = UUID.randomUUID().toString().replace("-", "");
			contactElem.addAttribute("id", uuid);//Ϊ�ӱ�ǩ���id����
			
			//Ϊ�ӱ�ǩ����ı���ǩ,��ͨ��contact�����ȡ��Ӧ���ı�����
			contactElem.addElement("name").setText(contact.getName());
			contactElem.addElement("sex").setText(contact.getSex());
			contactElem.addElement("age").setText(contact.getAge()+"");//ע������ת���ַ���
			contactElem.addElement("phone").setText(contact.getPhone());
			contactElem.addElement("email").setText(contact.getEmail());
			contactElem.addElement("qq").setText(contact.getQq());
			
			//��Documentд����xml�ļ�
			XMLUtil.writeXmlDoc(doc,PATHNAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * �޸���ϵ��
	 */
	public void updateContact(Contact contact) {

		/**
		 * �޸�idΪ2����ϵ��
		 * 1.��ѯidΪ2����ϵ�ˣ�ʹ��xpath������
		 * 2.�޸�contact��ǩ������
		 */
		
		try {
			//1.��ȡxml�ļ�
			Document doc = new SAXReader().read(new File(PATHNAME));
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='"+contact.getId()+"']");
			
			//2.�޸�contactElem��ǩ����
			contactElem.element("name").setText(contact.getName());
			contactElem.element("sex").setText(contact.getSex());
			contactElem.element("age").setText(contact.getAge()+"");
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("email").setText(contact.getEmail());
			contactElem.element("qq").setText(contact.getQq());
			
			//3.��doc����д����xml�ļ�
			XMLUtil.writeXmlDoc(doc, PATHNAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 * ɾ����ϵ��
	 */
	public void deleteContact(String id) {

		try {
			//1.��ȡxml�ļ�
			Document doc = new SAXReader().read(new File(PATHNAME));
			//2.����id��ѯ����ϵ�˶�Ӧ�ı�ǩ
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='"+id+"']");//ע��xpath�﷨����д
			//3.ɾ����ѯ�������ǩ
			if(contactElem!=null){
				contactElem.detach();
			}
			//4.���޸ĺ��doc����д�ص�xml�ļ�
			XMLUtil.writeXmlDoc(doc, PATHNAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * ��ѯ������ϵ��
	 */
	public List<Contact> findAll() {

		//2.����List����
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			//1.��ȡxml�ļ�
			Document doc = new SAXReader().read(new File(PATHNAME));
			
			//3.��ȡcontact��ǩ
			List<Element> docNodes = (List<Element>)doc.selectNodes("//contact");
			for (Element element : docNodes) {
				//����Contact����
				Contact contact = new Contact();
				contact.setId(element.attributeValue("id"));
				contact.setAge(Integer.parseInt(element.elementText("age")));
				contact.setEmail(element.elementText("email"));
				contact.setName(element.elementText("name"));
				contact.setPhone(element.elementText("phone"));
				contact.setQq(element.elementText("qq"));
				contact.setSex(element.elementText("sex"));
				
				//����װ�õ�contact�������ArrayList��
				contacts.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return contacts;
	}

	/**
	 * ���ݱ�Ų�ѯ��ϵ��
	 */
	public Contact findById(String id) {

		Contact c=null;
		//��ȡxml�ĵ�
		try {
			Document doc = new SAXReader().read(new File(PATHNAME));
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='"+id+"']");//ʹ��xpath����
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
	 * true:�ظ�
	 * false:���ظ�
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
