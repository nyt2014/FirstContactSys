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
 * �޸���ϵ���߼�
 * ����id�ҳ���Ӧ����ϵ�ˣ����û��޸ĺ�ѱ��ύ���ɼ��ϸ��Ƿ�������Ӧid����ϵ����Ϣ
 * ����ض���������ϵ����ҳ
 * @author Administrator
 *
 */


public class UpdataContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//1.���ղ���
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		
		//��װ��Contact����
		Contact contact = new Contact();
		contact.setId(id);
		contact.setName(name);
		contact.setSex(sex);
		contact.setAge(Integer.parseInt(age));
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setQq(qq);
		
		//2.����service��������ϵ�˵ķ���
		ContactService service = new ContactServiceImpl();
		try {
			service.updateContact(contact);
		} catch (NameRepeatException e) {
			//�Զ��崦��ҵ���쳣
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/updataContact.isp").forward(request, response);
			//���أ�ֹͣ��������ִ��
			return ;
		}
		
		//3.��ת����ѯ��ϵ�˵�ҳ��
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
