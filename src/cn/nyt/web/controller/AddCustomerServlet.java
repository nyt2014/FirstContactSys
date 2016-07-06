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

	// ͨ�������Ĭ�ϵ�get�ύ��ʽ���÷������Զ������ж�Ӧ��doget�������û��ṩ����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("sexs", Globals.sexs);
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);

		request.getRequestDispatcher("/WEB-INF/jsp/addcustomer.jsp").forward(
				request, response);

	}

	// �û��ύ������ʱ�������ύ��ʽΪpost���÷���������dopost���������û����������
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			// ��У��
			
			
			// �ѱ���װ��customer������
			Customer c = WebUtils.request2Bean(request, Customer.class);
			c.setId(WebUtils.generateID());

			BusinessService service = new BusinessServiceImpl();
			service.addCustomer(c);
			request.setAttribute("message", "��ӳɹ�����");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "���ʧ�ܣ�~");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}
