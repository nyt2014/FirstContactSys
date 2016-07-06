package cn.nyt.Servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.entity.Contact;
import cn.nyt.service.ContactService;
import cn.nyt.service.Impl.ContactServiceImpl;

public class QueryContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

				//1.����id
				String id = request.getParameter("id");
				
				//2.����dao����id��ѯ��ϵ�˵ķ���
				ContactService service = new ContactServiceImpl();
				Contact contact = service.findById(id);
				
//				System.out.println(contact.getSex()+"\t"+id+"\t"+dao+"\t"+contact);
				
				//3.��contact���󱣴浽�������
				request.setAttribute("contact", contact);
				
				//4.ת����updatatContact.jspҳ��
				request.getRequestDispatcher("/updataContact.jsp").forward(request, response);
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
