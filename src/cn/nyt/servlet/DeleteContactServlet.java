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
 * ɾ����ϵ���߼�
 * ���������͹�����id,ͨ��daoɾ�����id��Ӧ����ϵ��
 * @author Administrator
 *
 */
public class DeleteContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�����͹�����id
		String id = request.getParameter("id");
		//����dao�����ڲ�������
		ContactService service = new ContactServiceImpl();
		//ɾ����ϵ��
		service.deleteContact(id);
		//������ض���������ϵ����ҳ
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
