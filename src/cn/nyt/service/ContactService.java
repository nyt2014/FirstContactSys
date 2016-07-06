package cn.nyt.service;

import java.util.List;

import cn.nyt.entity.Contact;
import cn.nyt.exception.NameRepeatException;

public interface ContactService {

	//��1�������ϵ��
		public void addContact(Contact contact) throws NameRepeatException;
		//��2���޸���ϵ��
		public void updateContact(Contact contact) throws NameRepeatException;
//		��3��ɾ����ϵ��
		public void deleteContact(String id);
//		��4����ѯ������ϵ��
		public List<Contact> findAll();
		//5.���ݱ�Ų�ѯ��ϵ��
		public Contact findById(String id);
}
