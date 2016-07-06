package cn.nyt.service;

import java.util.List;

import cn.nyt.domian.Customer;
import cn.nyt.domian.PageBean;
import cn.nyt.domian.QueryInfo;

public interface BusinessService {

	void addCustomer(Customer c);

	void deleteCustomer(String id);

	void updataCustomer(Customer c);

	Customer findCustomer(String id);

	List<Customer> getAllCustomer();
	
	public PageBean pageQuery(QueryInfo info);

}