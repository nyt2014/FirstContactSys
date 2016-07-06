package cn.nyt.Util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC������
 * 
 * @author Administrator
 * 
 */
public class JDBC_Utils {
	
	private static Properties config=new Properties();
	
	/**
	 * �������ݿ�����������ȡ����
	 * ͨ����ȡ�����ļ�������ָ���������Ͷ�ȡurl��user��password
	 * �������������ӵĻ�ȡֻ��Ҫִ��һ�Σ����ָ���ھ�̬����鵱��ִ��
	 */
	static{
		try {
			InputStream in = JDBC_Utils.class.getClassLoader().getResourceAsStream("db.properties");
			//�������ļ����ؽ�config����
			config.load(in);
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 *��ȡ���� 
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(config.getProperty("url"), config.getProperty("user"), config.getProperty("password"));
		return conn;
	}

	/**
	 * �ر���Դ
	 */
	public static void release(ResultSet rset, Statement sta, Connection conn) {
		if (rset != null) {
			try {
				rset.close();
			} catch (Exception e) {
				e.printStackTrace();// ֻ��ӡ�쳣��Ϣ�����׳�
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
