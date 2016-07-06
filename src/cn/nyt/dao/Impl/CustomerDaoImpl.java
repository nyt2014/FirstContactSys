package cn.nyt.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.nyt.dao.CustomerDao;
import cn.nyt.domian.Customer;
import cn.nyt.domian.QueryResult;
import cn.nyt.exception.DaoException;
import cn.nyt.utils.DaoUtils;
import cn.nyt.utils.JDBC_Utils;

/**
 * 
 *CRUD
 */

/**
 * 模板代码： Connection conn=null; PreparedStatement st=null; ResultSet rs=null;
 * 
 * try { conn = JDBC_Utils.getConnection();
 * 
 * } catch (Exception e) { throw new DaoException(e); }finally{
 * JDBC_Utils.release(rs, st, conn); }
 */
public class CustomerDaoImpl implements CustomerDao {

	// 添加
	public void add(Customer c) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Utils.getConnection();

			String sql = "insert into customer(id,name,sex,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?);";
			st = conn.prepareStatement(sql);// 与编译
			st.setString(1, c.getId());
			st.setString(2, c.getName());
			st.setString(3, c.getSex());
			st.setDate(4, new java.sql.Date(c.getBirthday().getTime()));
			st.setString(5, c.getCellphone());
			st.setString(6, c.getEmail());
			st.setString(7, c.getPreference());
			st.setString(8, c.getType());
			st.setString(9, c.getDescription());

			// 添加到数据库
			st.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JDBC_Utils.release(rs, st, conn);
		}
	}

	// 更新
	public void updata(Customer c) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Utils.getConnection();

			String sql = "update customer set name=?,sex=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=?  where id=?;";
			st = conn.prepareStatement(sql);
			st.setString(1, c.getName());
			st.setString(2, c.getSex());
			st.setDate(3, new java.sql.Date(c.getBirthday().getTime()));
			st.setString(4, c.getCellphone());
			st.setString(5, c.getEmail());
			st.setString(6, c.getPreference());
			st.setString(7, c.getType());
			st.setString(8, c.getDescription());
			st.setString(9, c.getId());
			st.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JDBC_Utils.release(rs, st, conn);
		}
	}

	// 删除
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Utils.getConnection();
			String sql = "delete from customer where id=?;";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.executeUpdate();

		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JDBC_Utils.release(rs, st, conn);
		}
	}

	// 查找
	public Customer find(String id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Utils.getConnection();

			String sql = "select * from customer where id=?;";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs = st.executeQuery();// 注意查找用Query
			if (rs.next()) {
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setSex(rs.getString("sex"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				return c;
			}

		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JDBC_Utils.release(rs, st, conn);
		}
		return null;
	}

	// 获取所有客户信息
	public List<Customer> getAll() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Utils.getConnection();

			List<Customer> list = new ArrayList<Customer>();
			String sql = "select * from customer;";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();// 注意查找用Query
			while (rs.next()) {
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setEmail(rs.getString("email"));
				c.setSex(rs.getString("sex"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));

				if (rs.getString("description").getBytes().length > 16) {
					String desMix = DaoUtils.bSubstring(
							rs.getString("description"), 16);
					c.setDescription(desMix + "……");
				} else {
					c.setDescription(rs.getString("description"));
				}

				// if(rs.getString("description").length()>8){
				// String desMix="";
				// String desSub = rs.getString("description").substring(0, 8);
				// desMix=desSub+"…………";
				// c.setDescription(desMix);
				// }else{
				// c.setDescription(rs.getString("description"));
				// }

				list.add(c);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JDBC_Utils.release(rs, st, conn);
		}
	}
	
	
	
	/**
	 * ######
	 */
	//封装从数据库获取的分页数据（页面数据和总的页面数）到对象
	public QueryResult pageQuery(int startIndex,int pageSize){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Utils.getConnection();

			List<Customer> list = new ArrayList<Customer>();
			String sql = "select * from customer limit ?,?;";
			st = conn.prepareStatement(sql);//预编译
			st.setInt(1, startIndex);
			st.setInt(2, pageSize);
			rs = st.executeQuery();// 注意查找用Query
			while (rs.next()) {
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setEmail(rs.getString("email"));
				c.setSex(rs.getString("sex"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));

				if (rs.getString("description").getBytes().length > 16) {
					String desMix = DaoUtils.bSubstring(
							rs.getString("description"), 16);
					c.setDescription(desMix + "……");
				} else {
					c.setDescription(rs.getString("description"));
				}

				list.add(c);
			}
			QueryResult qr=new QueryResult();
			qr.setList(list);
			sql="select count(*) from customer;";
			 st = conn.prepareStatement(sql);
			 rs=st.executeQuery();
			 if(rs.next()){
				 qr.setTotalrecord(rs.getInt(1));
			 }
			
			return qr;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JDBC_Utils.release(rs, st, conn);
		}
	}
}
