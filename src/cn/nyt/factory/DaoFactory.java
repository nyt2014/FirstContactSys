package cn.nyt.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

	private Properties daoConfig=new Properties();

	private DaoFactory(){
		//��ȡdao�����ļ�,ֻ��ȡһ��
		InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
		try {
			daoConfig.load(in);//ͨ������ȡ���ݷ�װ��properties����
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}
	private static DaoFactory instance=new DaoFactory();
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public <T> T createDao(Class<T> clazz){
		
//		clazz.getName();//��������������ƣ����磺cn.nyt.dao.Impl.UserDaoJdbcImpl
		String name = clazz.getSimpleName();//������ļ����� ���磺UserDaoJdbcImpl
		String className = daoConfig.getProperty(name);
		
		try {
			T dao = (T) Class.forName(className).newInstance();
			return dao;
		} catch (Exception e) {
			throw new RuntimeException();
		} 
		
	}
}
