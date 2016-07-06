package cn.nyt.Util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类
 * 
 * @author Administrator
 * 
 */
public class JDBC_Utils {
	
	private static Properties config=new Properties();
	
	/**
	 * 加载数据库驱动，并获取链接
	 * 通过读取配置文件来加载指定的驱动和读取url、user、password
	 * 由于驱动和链接的获取只需要执行一次，因此指定在静态代码块当中执行
	 */
	static{
		try {
			InputStream in = JDBC_Utils.class.getClassLoader().getResourceAsStream("db.properties");
			//把配置文件加载进config对象
			config.load(in);
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 *获取链接 
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(config.getProperty("url"), config.getProperty("user"), config.getProperty("password"));
		return conn;
	}

	/**
	 * 关闭资源
	 */
	public static void release(ResultSet rset, Statement sta, Connection conn) {
		if (rset != null) {
			try {
				rset.close();
			} catch (Exception e) {
				e.printStackTrace();// 只打印异常信息，不抛出
			}
		}
		if (sta != null) {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
