package junit.test;

import org.junit.Test;

public class Demo {

	@Test
	public void test(){
		
		String str="一个好人！good man!";
		
		byte[] bytes = str.getBytes();
		
		for (byte b : bytes) {
			System.out.println(b);
			
		}
		System.out.println("length:"+str.length());
	}
	
	@Test
	public void testBSubstring() {

		String str = "一个好人！good man!";

		try {
			String bSubstring = cn.nyt.utils.DaoUtils.bSubstring(str, 16);
			System.out.println(bSubstring);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
