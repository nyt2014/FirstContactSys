package cn.nyt.service.Impl;

import java.util.List;

import cn.nyt.dao.CustomerDao;
import cn.nyt.domian.Customer;
import cn.nyt.domian.PageBean;
import cn.nyt.domian.QueryInfo;
import cn.nyt.domian.QueryResult;
import cn.nyt.factory.DaoFactory;
import cn.nyt.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {

	CustomerDao dao=DaoFactory.getInstance().createDao(CustomerDao.class);
	
	public void addCustomer(Customer c){
		
		dao.add(c);
	}
	
	public void deleteCustomer(String id){
		dao.delete(id);
	}
	
	public void updataCustomer(Customer c){
		dao.updata(c);
	}
	
	public Customer findCustomer(String id){
		return dao.find(id);
	}
	
	public List<Customer> getAllCustomer(){
		
		
		return dao.getAll();
	}
	
	
	/**
	 *########### 
	 */
	public PageBean pageQuery(QueryInfo info){
		
		PageBean pb=new PageBean();
		
		QueryResult qr = dao.pageQuery(info.getStartindex(), info.getPagesize());
		
		pb.setList(qr.getList());
		pb.setTotalrecord(qr.getTotalrecord());
		pb.setPagesize(info.getPagesize());
		pb.setCurrentpage(info.getCurrentpage());
		
		return pb;
	}
}
