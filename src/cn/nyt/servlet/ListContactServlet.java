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
 * 显示所有的联系人，首页
 * @author Administrator
 *
 */
public class ListContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.关联Dao（数据访问对象），读取xml中的联系人数据
		ContactService service = new ContactServiceImpl();
		//2.获取所有联系人，存储到list集合中
		List<Contact> list = service.findAll();
		
		//3.把结果保存到request域对象中
		request.setAttribute("contacts", list);
		
		
		//4.转发到jsp页面
		request.getRequestDispatcher("/listContact.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
