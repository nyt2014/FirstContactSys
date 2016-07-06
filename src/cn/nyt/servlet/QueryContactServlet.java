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

				//1.接收id
				String id = request.getParameter("id");
				
				//2.调用dao根据id查询联系人的方法
				ContactService service = new ContactServiceImpl();
				Contact contact = service.findById(id);
				
//				System.out.println(contact.getSex()+"\t"+id+"\t"+dao+"\t"+contact);
				
				//3.将contact对象保存到域对象中
				request.setAttribute("contact", contact);
				
				//4.转发到updatatContact.jsp页面
				request.getRequestDispatcher("/updataContact.jsp").forward(request, response);
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
