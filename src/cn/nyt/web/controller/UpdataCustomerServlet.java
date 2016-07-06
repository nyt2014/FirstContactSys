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
 * �޸���ϵ��
 *
 */
public class UpdataCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��������
		//1.�ѱ���Globals��һ���ֵı��ṹ���ݴ���request
		//2.�����ύ������id��ȡ��Ӧ��Customer���󣬲�����request����
		//3.����ת����editCustomer.jspҳ��
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
		//1.��ΪҪ���������͹��������ݣ�����޸�������ַ���
		//2.����ҵ���Service����
		//3.�����󣨱�editCustomet.jsp���ύ������������webutils���߷�װ������
		//4.����ҵ����updataCustomer�������������޸ĺõĿͻ��������ȥ
		try {
			BusinessService service = new BusinessServiceImpl();
			
			Customer c = WebUtils.request2Bean(request,Customer.class );
//			System.out.println(c);
			service.updataCustomer(c);
			request.setAttribute("message", "�޸ĳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�޸�ʧ�ܣ�");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
