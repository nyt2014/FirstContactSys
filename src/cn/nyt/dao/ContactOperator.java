package cn.nyt.dao;

import java.util.List;

import cn.nyt.entity.Contact;
/**
 * ��dao���ܣ���ɾ�Ĳ飩���������������ɽӿ�
 * @author Administrator
 *
 */

public interface ContactOperator {

	//��1�������ϵ��
	public void addContact(Contact contact);
	//��2���޸���ϵ��
	public void updateContact(Contact contact);
//	��3��ɾ����ϵ��
	public void deleteContact(String id);
//	��4����ѯ������ϵ��
	public List<Contact> findAll();
	//5.���ݱ�Ų�ѯ��ϵ��
	public Contact findById(String id);
	
	//����������ѯ�Ƿ��ظ�
	public boolean checkContact(String name);
}
