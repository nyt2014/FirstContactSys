package cn.nyt.utils;

import org.junit.Test;

public class DaoUtils {

	public static String bSubstring(String s, int length) throws Exception {

		byte[] bytes = s.getBytes("Unicode");
		int n = 0; // ��ʾ��ǰ���ֽ���
		int i = 2; // Ҫ��ȡ���ֽ������ӵ�3���ֽڿ�ʼ
		for (; i < bytes.length && n < length; i++) {
			// ����λ�ã���3��5��7�ȣ�ΪUCS2�����������ֽڵĵڶ����ֽ�
			if (i % 2 == 1) {
				n++; // ��UCS2�ڶ����ֽ�ʱn��1
			} else {
				// ��UCS2����ĵ�һ���ֽڲ�����0ʱ����UCS2�ַ�Ϊ���֣�һ�������������ֽ�
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		// ���iΪ����ʱ�������ż��
		if (i % 2 == 1)

		{
			// ��UCS2�ַ��Ǻ���ʱ��ȥ�������һ��ĺ���
			if (bytes[i - 1] != 0)
				i = i - 1;
			// ��UCS2�ַ�����ĸ�����֣��������ַ�
			else
				i = i + 1;
		}

		return new String(bytes, 0, i, "Unicode");
	}

	
}
