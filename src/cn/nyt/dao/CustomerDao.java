package cn.nyt.dao;

import java.util.List;

import cn.nyt.domian.Customer;
import cn.nyt.domian.QueryResult;

public interface CustomerDao {

	//���
	void add(Customer c);

	//����
	void updata(Customer c);

	//ɾ��
	void delete(String id);

	//����
	Customer find(String id);

	//��ȡ���пͻ���Ϣ
	List<Customer> getAll();
	
	public QueryResult pageQuery(int startIndex,int pageSize);

}