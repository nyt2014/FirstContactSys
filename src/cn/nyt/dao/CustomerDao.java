package cn.nyt.dao;

import java.util.List;

import cn.nyt.domian.Customer;
import cn.nyt.domian.QueryResult;

public interface CustomerDao {

	//添加
	void add(Customer c);

	//更新
	void updata(Customer c);

	//删除
	void delete(String id);

	//查找
	Customer find(String id);

	//获取所有客户信息
	List<Customer> getAll();
	
	public QueryResult pageQuery(int startIndex,int pageSize);

}