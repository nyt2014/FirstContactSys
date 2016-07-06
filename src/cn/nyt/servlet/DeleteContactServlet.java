package cn.nyt.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.dao.Impl.ContactOperatorImpl;
import cn.nyt.service.ContactService;
import cn.nyt.service.Impl.ContactServiceImpl;
/**
 * 删除联系人逻辑
 * 根据请求传送过来的id,通过dao删除这个id对应的联系人
 * @author Administrator
 *
 */
public class DeleteContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//获取请求传送过来的id
		String id = request.getParameter("id");
		//关联dao，用于操作数据
		ContactService service = new ContactServiceImpl();
		//删除联系人
		service.deleteContact(id);
		//浏览器重定向到搜有联系人首页
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
