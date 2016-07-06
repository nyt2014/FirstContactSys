package cn.nyt.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nyt.domian.PageBean;
import cn.nyt.domian.QueryInfo;
import cn.nyt.service.BusinessService;
import cn.nyt.service.Impl.BusinessServiceImpl;
import cn.nyt.utils.WebUtils;

public class ListCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * ###############
		 */

		try{
			//从请求中获取分页数据封装到QueryInfo对象
			QueryInfo qi=WebUtils.request2Bean(request, QueryInfo.class);
			
			BusinessService service = new BusinessServiceImpl();
			//从业务层获取PageBean对象并保存都request域中
			PageBean pb = service.pageQuery(qi);
			request.setAttribute("pagebean", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/listcustomer.jsp").forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "查看客户失败！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
