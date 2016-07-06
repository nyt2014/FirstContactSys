package cn.nyt.Servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.dao.Impl.ContactOperatorImpl;
import cn.nyt.entity.Contact;
import cn.nyt.exception.NameRepeatException;
import cn.nyt.service.ContactService;
import cn.nyt.service.Impl.ContactServiceImpl;
/**
 * 修改联系人逻辑
 * 根据id找出对应的联系人，在用户修改后把表单提交即可集合覆盖服务器对应id的联系人信息
 * 最后重定向到所有联系人首页
 * @author Administrator
 *
 */


public class UpdataContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//1.接收参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		
		//封装成Contact对象
		Contact contact = new Contact();
		contact.setId(id);
		contact.setName(name);
		contact.setSex(sex);
		contact.setAge(Integer.parseInt(age));
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setQq(qq);
		
		//2.调用service类的添加联系人的方法
		ContactService service = new ContactServiceImpl();
		try {
			service.updateContact(contact);
		} catch (NameRepeatException e) {
			//自定义处理业务异常
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/updataContact.isp").forward(request, response);
			//返回，停止继续往下执行
			return ;
		}
		
		//3.跳转到查询联系人的页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
