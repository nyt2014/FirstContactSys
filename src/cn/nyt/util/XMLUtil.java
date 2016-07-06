package cn.nyt.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * xml������
 * @author Administrator
 *
 */
public class XMLUtil {
/**
 * д��xml�ļ�
 */
	public static void writeXmlDoc(Document doc ,String pathName){
		try {
			FileOutputStream out = new FileOutputStream(pathName);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			//����xml�����
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
