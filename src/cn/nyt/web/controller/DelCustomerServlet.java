package cn.nyt.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.service.Impl.BusinessServiceImpl;

public class DelCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//根据id删除联系人
		//1.获取请求域中提交过来的客户id
		//2.创建业务层对象
		//3.业务层对象的删除方法传入id，删除客户
		//4.请求转发，提示删除状态信息
		
		try {
			String id = request.getParameter("id");
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.deleteCustomer(id);
			request.setAttribute("message", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
