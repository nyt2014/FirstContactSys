package cn.nyt.domian;

import java.util.List;

public class QueryResult {
	
	private List list;   //记住用户看的页的数据
	private int totalrecord;    //记往总记录数
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
}
