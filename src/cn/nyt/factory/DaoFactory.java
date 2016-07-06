package cn.nyt.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

	private Properties daoConfig=new Properties();

	private DaoFactory(){
		//读取dao配置文件,只读取一次
		InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
		try {
			daoConfig.load(in);//通过流获取数据封装到properties对象
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}
	private static DaoFactory instance=new DaoFactory();
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public <T> T createDao(Class<T> clazz){
		
//		clazz.getName();//返回类的完整名称，比如：cn.nyt.dao.Impl.UserDaoJdbcImpl
		String name = clazz.getSimpleName();//返回类的简单名称 比如：UserDaoJdbcImpl
		String className = daoConfig.getProperty(name);
		
		try {
			T dao = (T) Class.forName(className).newInstance();
			return dao;
		} catch (Exception e) {
			throw new RuntimeException();
		} 
		
	}
}
