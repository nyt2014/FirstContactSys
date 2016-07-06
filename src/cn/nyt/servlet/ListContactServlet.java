package cn.nyt.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.entity.Contact;
import cn.nyt.service.ContactService;
import cn.nyt.service.Impl.ContactServiceImpl;
/**
 * ��ʾ���е���ϵ�ˣ���ҳ
 * @author Administrator
 *
 */
public class ListContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.����Dao�����ݷ��ʶ��󣩣���ȡxml�е���ϵ������
		ContactService service = new ContactServiceImpl();
		//2.��ȡ������ϵ�ˣ��洢��list������
		List<Contact> list = service.findAll();
		
		//3.�ѽ�����浽request�������
		request.setAttribute("contacts", list);
		
		
		//4.ת����jspҳ��
		request.getRequestDispatcher("/listContact.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
