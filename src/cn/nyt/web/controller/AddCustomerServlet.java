package cn.nyt.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.domian.Customer;
import cn.nyt.service.BusinessService;
import cn.nyt.service.Impl.BusinessServiceImpl;
import cn.nyt.utils.Globals;
import cn.nyt.utils.WebUtils;

public class AddCustomerServlet extends HttpServlet {

	// 通过浏览器默认的get提交方式，让服务器自动调用行对应的doget方法给用户提供界面
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("sexs", Globals.sexs);
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);

		request.getRequestDispatcher("/WEB-INF/jsp/addcustomer.jsp").forward(
				request, response);

	}

	// 用户提交表单数据时，设置提交方式为post，让服务器调用dopost方法处理用户请求的数据
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			// 表单校验
			
			
			// 把表单封装到customer对象中
			Customer c = WebUtils.request2Bean(request, Customer.class);
			c.setId(WebUtils.generateID());

			BusinessService service = new BusinessServiceImpl();
			service.addCustomer(c);
			request.setAttribute("message", "添加成功！！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败！~");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}
