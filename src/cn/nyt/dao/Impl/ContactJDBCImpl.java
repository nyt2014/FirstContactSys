package cn.nyt.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import cn.nyt.Util.JDBC_Utils;
import cn.nyt.dao.ContactOperator;
import cn.nyt.entity.Contact;
/**
	  create database day14_ContactJDBC;
	  use day14_ContactJDBC;
	  create table contact
	  (
	  	id varchar(50) primary key,
	  	name varchar(20) not null unique,
	  	sex varchar(10) not null,
	  	age int,
	  	phone varchar(20) unique,
	  	email varchar(40) unique,
	  	qq varchar(40) unique
	  );
	  
	  insert into contact(id,name,sex,age,phone,email,qq) values('1','wangqi','sex',23,'15698745621','564@qq.com','256415698');
 
 */
public class ContactJDBCImpl implements ContactOperator {

	/**
	 * 添加联系人
	 */
	public void addContact(Contact contact) {

		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rset = null;
		try {
			conn=JDBC_Utils.getConnection();
			
			String sql="insert into contact(id,name,sex,age,phone,email,qq) values(?,?,?,?,?,?,?);";
			
			sta = conn.prepareStatement(sql);//对sql进行预编译
			String id = UUID.randomUUID().toString().replace("-", "");
			sta.setString(1,id);
			sta.setString(2,contact.getName());
			sta.setString(3,contact.getSex());
			sta.setInt(4,contact.getAge());
			sta.setString(5,contact.getPhone());
			sta.setString(6,contact.getEmail());
			sta.setString(7,contact.getQq());
			int num = sta.executeUpdate();
			if(num>0){
				System.out.println("添加成功！");
			}
		}catch(SQLException e){
				e.printStackTrace();
				throw new RuntimeException();
		} finally {
			JDBC_Utils.release(rset, sta, conn);
		}
	}

	/**
	 * 修改联系人
	 */
	public void updateContact(Contact contact) {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rset = null;
		try {
			conn=JDBC_Utils.getConnection();
			String sql="update contact set name=?,sex=?,age=?,phone=?,email=?,qq=? where id=?;";
			sta = conn.prepareStatement(sql);//对sql进行预编译
			sta.setString(1,contact.getName());
			sta.setString(2,contact.getSex());
			sta.setInt(3,contact.getAge());
			sta.setString(4,contact.getPhone());
			sta.setString(5,contact.getEmail());
			sta.setString(6,contact.getQq());
			sta.setString(7,contact.getId());
			int num = sta.executeUpdate();
		}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException();
		} finally {
			JDBC_Utils.release(rset, sta, conn);
		}
	}

	/**
	 * 删除联系人
	 */
	public void deleteContact(String id) {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rset = null;
		try {
			conn=JDBC_Utils.getConnection();
			String sql="delete  from contact where id=?;";
			sta = conn.prepareStatement(sql);//对sql进行预编译
			sta.setString(1,id);
			int num = sta.executeUpdate();//发送
			if(num>0){
				System.out.println("删除成功！");
			}
		}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException();
		} finally {
			JDBC_Utils.release(rset, sta, conn);
		}

	}

	/**
	 * 查询所有联系人
	 */
	@Test
	public List<Contact> findAll() {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rset = null;
		try {
			conn=JDBC_Utils.getConnection();
			String sql="select * from contact;";
			sta = conn.prepareStatement(sql);
			
			rset = sta.executeQuery();
			List<Contact> list=new ArrayList<Contact>();
			while (rset.next()) {
				Contact con = new Contact();
				con.setId(rset.getString("id"));
				con.setName(rset.getString("name"));
				con.setSex(rset.getString("sex"));
				con.setAge(rset.getInt("age"));
				con.setPhone(rset.getString("phone"));
				con.setEmail(rset.getString("email"));
				con.setQq(rset.getString("qq"));
				list.add(con);
			}
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JDBC_Utils.release(rset, sta, conn);
		}
		
	}

	/**
	 * 根据id查联系人
	 */
	public Contact findById(String id) {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rset = null;
		try {
			conn=JDBC_Utils.getConnection();
			String sql="select * from contact where id=?;";
			sta = conn.prepareStatement(sql);
			sta.setString(1, id);
			rset = sta.executeQuery();
			Contact con = null;
			if(rset.next()) {
				con = new Contact();
				con.setId(rset.getString("id"));
				con.setName(rset.getString("name"));
				con.setSex(rset.getString("sex"));
				con.setAge(rset.getInt("age"));
				con.setPhone(rset.getString("phone"));
				con.setEmail(rset.getString("email"));
				con.setQq(rset.getString("qq"));
			}
			return con;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JDBC_Utils.release(rset, sta, conn);
		}
	}

	/**
	 * 姓名检查
	 */
	public boolean checkContact(String name) {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rset = null;
		try {
			conn=JDBC_Utils.getConnection();
			String sql="select * from contact where name=?";
			sta = conn.prepareStatement(sql);
			sta.setString(1, name);
			
			rset = sta.executeQuery();
			
			if(rset.next()) {
				return true;
			}else{
				return false;
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JDBC_Utils.release(rset, sta, conn);
		}
	}

}
