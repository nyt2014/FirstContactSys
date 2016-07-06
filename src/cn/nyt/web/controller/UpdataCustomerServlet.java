package cn.nyt.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.domian.Customer;
import cn.nyt.service.BusinessService;
import cn.nyt.service.Impl.BusinessServiceImpl;
import cn.nyt.utils.Globals;
import cn.nyt.utils.WebUtils;

/**
 * 修改联系人
 *
 */
public class UpdataCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//回显数据
		//1.把表单在Globals中一部分的表单结构内容存入request
		//2.根据提交过来的id获取对应的Customer对象，并存入request域中
		//3.请求转发到editCustomer.jsp页面
		request.setAttribute("sexs", Globals.sexs);
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);
		String id = request.getParameter("id");
		BusinessServiceImpl service = new BusinessServiceImpl();
		Customer c = service.findCustomer(id);
		request.setAttribute("c", c);
		
		request.getRequestDispatcher("/WEB-INF/jsp/editCustomer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.因为要处理请求发送过来的数据，因此修改请求的字符集
		//2.创建业务层Service对象
		//3.将请求（表单editCustomet.jsp）提交过来的数据用webutils工具封装到对象
		//4.调用业务层的updataCustomer方法，并将表单修改好的客户对象传入进去
		try {
			BusinessService service = new BusinessServiceImpl();
			
			Customer c = WebUtils.request2Bean(request,Customer.class );
//			System.out.println(c);
			service.updataCustomer(c);
			request.setAttribute("message", "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "修改失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
