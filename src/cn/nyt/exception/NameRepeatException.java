package cn.nyt.exception;
/**
 * 姓名重复自定义异常类
 * @author Administrator
 *
 */
public class NameRepeatException extends Exception {
	
	public NameRepeatException(String msg){
		super(msg);
	}

}
