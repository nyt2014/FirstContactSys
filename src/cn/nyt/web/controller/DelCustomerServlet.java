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

		//����idɾ����ϵ��
		//1.��ȡ���������ύ�����Ŀͻ�id
		//2.����ҵ������
		//3.ҵ�������ɾ����������id��ɾ���ͻ�
		//4.����ת������ʾɾ��״̬��Ϣ
		
		try {
			String id = request.getParameter("id");
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.deleteCustomer(id);
			request.setAttribute("message", "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ɾ��ʧ�ܣ�");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
